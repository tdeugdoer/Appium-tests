package pages.auth;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import pages.BasePage;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

@Getter
public class LoginPage extends BasePage {
    private final SelenideElement loginField = $(id("by.rw.client:id/login_edit_text"));
    private final SelenideElement passwordField = $(id("by.rw.client:id/password_edit_text"));
    private final SelenideElement loginButton = $(id("by.rw.client:id/button_login"));

}
