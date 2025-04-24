package components;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

@Getter
public class Navigation {
    private final SelenideElement orders = $(id("by.rw.client:id/navigation_orders"));
    private final SelenideElement profile = $(id("by.rw.client:id/navigation_profile"));

}
