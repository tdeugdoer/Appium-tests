package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public abstract class BasePage {
    public void fillInField(SelenideElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

}
