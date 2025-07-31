package com.example.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

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
		String action = actionRequest.getParameter("action");

		if ("connect".equals(action)) {
			System.out.println("Connecting to RBM...");
			// Add your RBM connection logic here
		} else if ("sendMessage".equals(action)) {
			String msisdn = actionRequest.getParameter("msisdn");
			String message = actionRequest.getParameter("message");

			System.out.println("Sending message to " + msisdn + ": " + message);

			try {
				// The region for the API endpoint, e.g., "us"
				String region = "us";

				// The unique ID for the agent
				String agentId = "your-agent-id"; // TODO: Replace with your agent ID

				// The user's phone number in E.164 format
				String userMsisdn = msisdn;

				// The content of the message
				Message messageContent = new Message().setText(message);

				// Create the request object
				SendRbmMessageRequest request = new SendRbmMessageRequest()
					.setAgentId(agentId)
					.setMsisdn(userMsisdn)
					.setMessage(messageContent);

				// Send the message
				rbmApi.projects().agents().messages().send(region, request).execute();

				System.out.println("Message sent successfully!");
			} catch (Exception e) {
				throw new PortletException(e);
			}
		}
	}
}