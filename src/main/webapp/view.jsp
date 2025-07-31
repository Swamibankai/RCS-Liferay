<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<div>
	<h2>RCS Liferay Portlet</h2>

	<p>This portlet connects to the Google RCS Business Messaging (RBM) platform.</p>

	<form action="<portlet:actionURL />" method="post">
		<input type="hidden" name="<portlet:namespace />action" value="connect">
		<button type="submit">Connect to RBM</button>
	</form>

	<hr>

	<h3>Send Message</h3>

	<form action="<portlet:actionURL />" method="post">
		<input type="hidden" name="<portlet:namespace />action" value="sendMessage">
		<div>
			<label for="<portlet:namespace />msisdn">Phone Number (E.164):</label>
			<input type="text" id="<portlet:namespace />msisdn" name="<portlet:namespace />msisdn">
		</div>
		<div>
			<label for="<portlet:namespace />message">Message:</label>
			<textarea id="<portlet:namespace />message" name="<portlet:namespace />message"></textarea>
		</div>
		<button type="submit">Send Message</button>
	</form>
</div>