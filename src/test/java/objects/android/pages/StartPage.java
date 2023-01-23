package objects.android.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class StartPage {

    private final static String pageName = "Page cart";

    protected final SelenideElement welcomeButtons = $x("//*[contains(@resource-id,'welcome_buttons')]");
    protected final SelenideElement moreOptions = $x("//*[contains(@resource-id,'more_signin_options')]");
    protected final SelenideElement emailOption = $x("//*[contains(@resource-id,'email_signup')]");

    @Step("Initial screen should be opened")
    public StartPage shouldBeOpen() {
        welcomeButtons.shouldBe(visible);
        return this;
    }

    @Step("Press on the 'Continue with other options' button")
    public StartPage clickMoreOptions() {
        moreOptions.shouldBe(visible).click();
        return this;
    }

    @Step("Press on the 'Register with email' button")
    public StartPage clickEmailOption() {
        emailOption.shouldBe(visible).click();
        return this;
    }
}
