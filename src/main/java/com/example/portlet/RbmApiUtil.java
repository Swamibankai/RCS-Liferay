package com.example.portlet;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.rcsbusinessmessaging.v1.Rcsbusinessmessaging;
import com.google.api.services.rcsbusinessmessaging.v1.RcsbusinessmessagingScopes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

public class RbmApiUtil {

	public static Rcsbusinessmessaging initiateRbmApi() throws IOException {
		GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream("credentials.json"))
			.createScoped(Collections.singleton(RcsbusinessmessagingScopes.RCSBUSINESSMESSAGING));

		return new Rcsbusinessmessaging.Builder(new NetHttpTransport(), new JacksonFactory(), credential)
			.setApplicationName("RCS Liferay Portlet")
			.build();
	}
}