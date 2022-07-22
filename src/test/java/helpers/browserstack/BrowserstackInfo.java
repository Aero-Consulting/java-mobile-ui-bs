package helpers.browserstack;

import static io.qameta.allure.Allure.addAttachment;
import static java.text.MessageFormat.format;

import helpers.config.TestConfiguration;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BrowserstackInfo {

    private static final OkHttpClient client = new OkHttpClient().newBuilder().build();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public AutomationSession automation_session;

    private static String getBrowserstackInfo(String sessionId) {
        String url = format("https://api-cloud.browserstack.com/app-automate/sessions/{0}.json", sessionId);
        String credential = Credentials.basic(TestConfiguration.getBSUser(), TestConfiguration.getBSPassword());

        Request request = new Request.Builder()
            .url(url)
            .header("Authorization", credential)
            .get()
            .build();
        String result = "Can't get browserstack info";
        try {
            Response response = client.newCall(request).execute();
            String body = response.body().string();
            BrowserstackInfo browserstackInfo = objectMapper.readValue(body, BrowserstackInfo.class);
            result = format(
                "<div width=\"600\">" +
                    "<a href=\"{0}\" target=\"_blank\">View test run on browserstack</a>" +
                    "<br>" +
                    "<video width=\"470\" height=\"255\" controls>" +
                    "    <source src=\"{1}\" type=\"video/mp4\"></video>" +
                    "</div>",
                browserstackInfo.automation_session.public_url,
                browserstackInfo.automation_session.video_url
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void attachBrowserstackInfo(String sessionId) {
        addAttachment("Browserstack", "text/html", getBrowserstackInfo(sessionId), "html");
    }

}
