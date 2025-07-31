package com.example.portlet;

import com.google.api.services.rcsbusinessmessaging.v1.Rcsbusinessmessaging;
import com.google.api.services.rcsbusinessmessaging.v1.model.Message;
import com.google.api.services.rcsbusinessmessaging.v1.model.SendRbmMessageRequest;
import com.google.gson.Gson;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=category.sample",
        "com.liferay.portlet.header-portlet-css=/css/main.css",
        "com.liferay.portlet.instanceable=true",
        "javax.portlet.display-name=RCS Liferay",
        "javax.portlet.init-param.template-path=/",
        "javax.portlet.init-param.view-template=/view.jsp",
        "javax.portlet.name=rcs_liferay",
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)
public class RcsLiferayPortlet extends MVCPortlet {

    private Rcsbusinessmessaging rbmApi;

    @Override
    public void init() throws PortletException {
        super.init();
        try {
            rbmApi = RbmApiUtil.initiateRbmApi();
        } catch (Exception e) {
            throw new PortletException(e);
        }
    }

    @Override
    public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
        String action = ParamUtil.getString(actionRequest, "action");

        if ("connect".equals(action)) {
            System.out.println("Connecting to RBM...");
            actionRequest.getPortletSession().setAttribute("connected", "true");
        } else if ("sendMessage".equals(action)) {
            String msisdn = ParamUtil.getString(actionRequest, "msisdn");
            String message = ParamUtil.getString(actionRequest, "message");

            System.out.println("Sending message to " + msisdn + ": " + message);

            try {
                String region = "us";
                String agentId = "your-agent-id"; // TODO: Replace with your agent ID
                String userMsisdn = msisdn;
                Message messageContent = new Message().setText(message);
                SendRbmMessageRequest request = new SendRbmMessageRequest()
                    .setAgentId(agentId)
                    .setMsisdn(userMsisdn)
                    .setMessage(messageContent);
                rbmApi.projects().agents().messages().send(region, request).execute();
                System.out.println("Message sent successfully!");
            } catch (Exception e) {
                throw new PortletException(e);
            }
        }
    }

    @Override
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
        String resourceID = resourceRequest.getResourceID();

        if ("webhook".equals(resourceID)) {
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = resourceRequest.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }
            System.out.println("Received webhook: " + sb.toString());
            resourceResponse.getWriter().write("{\"status\":\"ok\"}");
        } else if ("status".equals(resourceID)) {
            resourceResponse.setContentType("application/json");
            PrintWriter writer = resourceResponse.getWriter();
            Map<String, String> status = new HashMap<>();
            status.put("status", "ok");
            writer.print(new Gson().toJson(status));
            writer.flush();
        }
    }
}