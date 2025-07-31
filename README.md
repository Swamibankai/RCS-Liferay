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
