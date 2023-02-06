package objects.android.entries;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import objects.android.pages.StartPage;

import static com.codeborne.selenide.Condition.visible;

public class ProductCard {
    protected SelenideElement container;

    public ProductCard(SelenideElement container) {
        this.container = container;
    }

    @Step("Нажать на изображение карточки товара")
    public ProductCard clickProductImage() {
        container.$("image")
                .shouldBe(visible)
                .click();
        return this;
    }
}
