package objects.android.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import objects.android.entries.ProductCard;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static helpers.factories.ObjectProvider.instance;

public class StartPage {

    private final static String pageName = "Page cart";

    protected final SelenideElement welcomeButtons = $x("//*[contains(@resource-id,'welcome_buttons')]");
    protected final SelenideElement moreOptions = $x("//*[contains(@resource-id,'more_signin_options')]");
    protected final SelenideElement emailOption = $x("//*[contains(@resource-id,'email_signup')]");
    protected final ElementsCollection products = $$x("//*[@text='ite,']");

    @Step("Получить карточку товара с названием {name}")
    public ProductCard getProductByName(String name) {
        SelenideElement product = products.filter(text(name)).first();
        return instance(ProductCard.class, product);
    }

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
