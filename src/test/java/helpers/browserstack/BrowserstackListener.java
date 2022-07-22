package helpers.browserstack;

import com.codeborne.selenide.Selenide;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class BrowserstackListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        BrowserstackInfo.attachBrowserstackInfo(Selenide.sessionId().toString());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        BrowserstackInfo.attachBrowserstackInfo(Selenide.sessionId().toString());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        BrowserstackInfo.attachBrowserstackInfo(Selenide.sessionId().toString());
    }

}
