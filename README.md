# RCS Liferay Portlet

This project is a Liferay portlet that connects to the Google RCS Business Messaging (RBM) platform.

## Prerequisites

* [Liferay Portal](https://www.liferay.com/)
* [Apache Maven](https://maven.apache.org/)
* [Google Cloud SDK](https://cloud.google.com/sdk)

## Setup

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/rcs-liferay.git
   ```

2. **Configure your Google Cloud project:**

   * Follow the instructions in the [Google Cloud documentation](https://cloud.google.com/iam/docs/creating-managing-service-accounts) to create a service account.
   * Enable the **RCS Business Messaging API** for your project.
   * Download the service account credentials as a JSON file and save it as `credentials.json` in the `src/main/resources` directory.

3. **Build the portlet:**

   ```bash
   mvn clean package
   ```

4. **Deploy the portlet:**

   * Copy the generated `.war` file from the `target` directory to the `deploy` directory of your Liferay server.

## Usage

1. Add the **RCS Liferay** portlet to a page in your Liferay portal.
2. Use the form to send messages to users.

## Detailed Deployment Process

This guide provides a step-by-step process for deploying the RCS Liferay Portlet.

### 1. Environment Setup

*   **Liferay:** Ensure you have a running Liferay Portal instance. You can download it from the [Liferay website](https://www.liferay.com/downloads).
*   **Java:** Make sure you have Java Development Kit (JDK) version 8 or higher installed.
*   **Maven:** Install Apache Maven and configure it in your system's environment variables.

### 2. Google Cloud Project Configuration

*   **Create a Service Account:**
    *   Go to the [Google Cloud Console](https://console.cloud.google.com/).
    *   Navigate to **IAM & Admin > Service Accounts**.
    *   Click **Create Service Account**.
    *   Provide a name and description for the service account.
    *   Grant the **RCS Business Messaging Editor** role to the service account.
    *   Click **Done**.
*   **Enable the RBM API:**
    *   Go to the [Google Cloud API Library](https://console.cloud.google.com/apis/library).
    *   Search for **RCS Business Messaging API** and enable it for your project.
*   **Download Credentials:**
    *   In the **Service Accounts** list, click on the service account you created.
    *   Go to the **Keys** tab.
    *   Click **Add Key > Create new key**.
    *   Select **JSON** as the key type and click **Create**.
    *   Save the downloaded JSON file as `credentials.json` in the `src/main/resources` directory of the project.

### 3. Build the Portlet

*   Open a terminal or command prompt.
*   Navigate to the root directory of the `rcs-liferay` project.
*   Run the following command to build the portlet:

    ```bash
    mvn clean package
    ```

*   This will generate a `.war` file in the `target` directory.

### 4. Deploy to Liferay

*   **Copy the `.war` file:**
    *   Copy the generated `.war` file (e.g., `rcs-liferay-1.0.0-SNAPSHOT.war`) from the `target` directory.
*   **Deploy:**
    *   Paste the `.war` file into the `deploy` directory of your Liferay server (e.g., `liferay-ce-portal-7.4.3.110-ga110/deploy`).
*   **Verify Deployment:**
    *   Check the Liferay server logs to ensure the portlet has been deployed successfully.

### 5. Configure the Webhook

*   **Get the Webhook URL:**
    *   Add the **RCS Liferay** portlet to a page in your Liferay portal.
    *   The webhook URL will be displayed in the portlet.
*   **Set the Webhook in the Google Cloud Console:**
    *   Go to the [RCS Business Messaging API page](https://console.cloud.google.com/apis/api/rcsbusinessmessaging.googleapis.com).
    *   Click **Manage**.
    *   Select your agent.
    *   In the **Webhook** section, paste the webhook URL from the portlet.
    *   Click **Save**.

### 6. Test the Portlet

*   **Send a message:**
    *   Use the form in the portlet to send a message to a test device.
*   **Receive a message:**
    *   Send a message from the test device to your agent.
    *   Check the Liferay server logs to see the received message.
