package tests.authorization;

import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.testng.Tag;
import objects.android.pages.RegistrationPage;
import objects.android.pages.StartPage;
import org.testng.annotations.Test;
import tests.BaseTest;

import static helpers.factories.ObjectProvider.instance;
import static io.qameta.allure.Allure.step;

@Feature("Авторизация")
@Owner("Mvideo Admin")
public class AuthorizationTest extends BaseTest {

    @Test(description = "Проверка успешного перехода к регистрации")
    @AllureId("2")
    @Tag("FM03")
    public void openRegistration() {
        StartPage cartPage = instance(StartPage.class);
        RegistrationPage registrationPage = instance(RegistrationPage.class);
        cartPage
                .shouldBeOpen()
                .clickMoreOptions()
                .clickEmailOption();
        registrationPage
                .shouldBeOpen();
    }

    @Test(description = "Проверка доступности кнопки регистрации")
    @AllureId("3")
    @Feature("Авторизация")
    public void createAccountValidation() {
        StartPage cartPage = instance(StartPage.class);
        RegistrationPage registrationPage = instance(RegistrationPage.class);
        step("Стартовый экран открыт", () -> {
            cartPage
                    .shouldBeOpen();
        });
        step("Нажать на Продолжить с другими опциями", () -> {
            cartPage
                    .clickMoreOptions();
        });
        step("Нажать на опцию Зарегистрироваться с помощью email", () -> {
            cartPage
                    .clickEmailOption();
        });
        step("Экран регистрации открыт", () -> {
            registrationPage
                    .shouldBeOpen();
        });
        step("Ввести значение test@test.test в поле email", () -> {
            registrationPage
                    .setEmailField("test@test.test");
        });
        step("Ввести значение 12345678 в поле пароль", () -> {
            registrationPage
                    .setPasswordField("12345678");

        });
        step("Кнопка Создать аккаунт активна", () -> {
            registrationPage
                    .createAccountEnabled();
        });
    }


}
