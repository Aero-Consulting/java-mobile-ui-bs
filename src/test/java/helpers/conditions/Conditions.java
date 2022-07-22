package helpers.conditions;

import static helpers.AppiumHelper.isKeyboardShown;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public abstract class Conditions {

    public static ExpectedCondition<Boolean> keyboardIsShown() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return isKeyboardShown();
                } catch (Exception e) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return "Keyboard is shown";
            }
        };
    }

}
