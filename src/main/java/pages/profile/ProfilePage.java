package pages.profile;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import pages.BasePage;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

@Getter
public class ProfilePage extends BasePage {
    private final SelenideElement loginButton = $(id("by.rw.client:id/btn_login_from_profile"));
    private final SelenideElement passengersButton = $(id("by.rw.client:id/btn_passengers"));

}
