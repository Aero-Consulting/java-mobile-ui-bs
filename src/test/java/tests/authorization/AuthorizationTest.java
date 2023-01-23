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
@Owner("Admin")
public class AuthorizationTest extends BaseTest {

    @Test(description = "Check user can proceed to registration screen")
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

    @Test(description = "Check registration button is available")
    @AllureId("3")
    @Feature("Autorization")
    public void createAccountValidation() {
        StartPage cartPage = instance(StartPage.class);
        RegistrationPage registrationPage = instance(RegistrationPage.class);
        step("Initial screen should be opened", () -> {
            cartPage
                    .shouldBeOpen();
        });
        step("Press on 'Continue with other options' button", () -> {
            cartPage
                    .clickMoreOptions();
        });
        step("Press on 'Register with email' button", () -> {
            cartPage
                    .clickEmailOption();
        });
        step("Registration screen should be opened", () -> {
            registrationPage
                    .shouldBeOpen();
        });
        step("Enter value 'test@test.test' to the 'email' field", () -> {
            registrationPage
                    .setEmailField("test@test.test");
        });
        step("Enter value '12345678' to the password field", () -> {
            registrationPage
                    .setPasswordField("12345678");

        });
        step("'Create Account' button should be active", () -> {
            registrationPage
                    .createAccountEnabled();
        });
    }

}
