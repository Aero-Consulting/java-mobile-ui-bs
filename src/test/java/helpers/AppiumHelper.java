package helpers;

import static com.codeborne.selenide.Selenide.Wait;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.conditions.Conditions.keyboardIsShown;

import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HasOnScreenKeyboard;
import io.appium.java_client.HidesKeyboardWithKeyName;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.SupportsContextSwitching;

public class AppiumHelper {


    public static AndroidDriver getAndroidDriver() {
        return (AndroidDriver) WebDriverRunner.getWebDriver();
    }

    public static IOSDriver getIOSDriver() {
        return (IOSDriver) WebDriverRunner.getWebDriver();
    }

    public static AppiumDriver getDriver() {
        return isIOS() ? getIOSDriver() : getAndroidDriver();
    }

    public static boolean isIOS() {
        return getWebDriver() instanceof IOSDriver;
    }

    public static boolean isKeyboardShown() {
        return ((HasOnScreenKeyboard) getDriver()).isKeyboardShown();
    }

    public static void waitKeyboardShown() {
        Wait().until(keyboardIsShown());
    }

    public static void hideKeyboard(String keyName) {
        ((HidesKeyboardWithKeyName) getDriver()).hideKeyboard(keyName);
    }

    public static void hideKeyboard() {
        runNative(() -> {
            ((HidesKeyboardWithKeyName) getDriver()).hideKeyboard();
        });
    }

    public static void runNative(Runnable runnable) {
        SupportsContextSwitching switching = ((SupportsContextSwitching) getDriver());
        String context = switching.getContext();
        switching.context("NATIVE_APP");
        runnable.run();
        switching.context(context);
    }

}
