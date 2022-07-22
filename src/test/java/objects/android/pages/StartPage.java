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

    @Step("Стартовый экран открыт")
    public StartPage shouldBeOpen() {
        welcomeButtons.shouldBe(visible);
        return this;
    }

    @Step("Нажать на 'Продолжить с другими опциями'")
    public StartPage clickMoreOptions() {
        moreOptions.shouldBe(visible).click();
        return this;
    }

    @Step("Нажать на опцию 'Зарегистрироваться с помощью email'")
    public StartPage clickEmailOption() {
        emailOption.shouldBe(visible).click();
        return this;
    }
}
