package objects.ios.pages;

import io.qameta.allure.Step;
import objects.android.pages.StartPage;

import static com.codeborne.selenide.Condition.visible;

public class StartPageIOS extends StartPage {

    @Override
    @Step("Экран 'Корзина' открыт")
    public StartPage shouldBeOpen() {
        welcomeButtons.shouldBe(visible);
        return this;
    }

}
