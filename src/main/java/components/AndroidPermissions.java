package components;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

@Getter
public class AndroidPermissions {
    private final SelenideElement allowForegroundLocationTrackingButton = $(id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"));
    private final SelenideElement allowButton = $(id("com.android.permissioncontroller:id/permission_allow_button"));

    public void clickIfVisible(SelenideElement element) {
        if (element.is(visible)) {
            element.click();
        }
    }

}
