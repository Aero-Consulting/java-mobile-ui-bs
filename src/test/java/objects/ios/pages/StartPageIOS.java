package objects.ios.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import objects.android.pages.StartPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class StartPageIOS extends StartPage {

    protected final SelenideElement welcomeButtons = $x("//*[contains(@resource-id,'welcome_buttons_on_ios')]");

    @Override
    @Step("Initial screen should be opened")
    public StartPage shouldBeOpen() {
        welcomeButtons.shouldBe(visible);
        return this;
    }
}
