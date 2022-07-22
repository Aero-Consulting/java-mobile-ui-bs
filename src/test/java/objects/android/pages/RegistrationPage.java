package objects.android.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationPage {

    private final static String pageName = "Page cart";

    protected final SelenideElement emailField = $x("//*[contains(@text,'Your email')]/../..");
    protected final SelenideElement passwordField = $x("//*[contains(@text,'Your password')]/../..");
    protected final SelenideElement createAccount = $x("//android.widget.ScrollView/android.view.View/android.widget.Button");

    @Step("Экран регистрации открыт")
    public RegistrationPage shouldBeOpen() {
        emailField.shouldBe(visible);
        return this;
    }

    @Step("Ввести значение {text} в поле email")
    public RegistrationPage setEmailField(String text) {
        emailField.shouldBe(visible).sendKeys(text);
        return this;
    }

    @Step("Ввести значение {text} в поле пароль")
    public RegistrationPage setPasswordField(String text) {
        passwordField.shouldBe(visible).sendKeys(text);
        return this;
    }

    @Step("Кнопка 'Создать аккаунт' активна")
    public RegistrationPage createAccountEnabled() {
        createAccount.shouldBe(visible, enabled);
        return this;
    }
}
