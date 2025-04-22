package pages.welcome;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.androidUIAutomator;

@Getter
public class WelcomePage {
    private final SelenideElement continueWithoutRegisterButton = $(androidUIAutomator("text(\"Продолжить без регистрации\")"));

}
