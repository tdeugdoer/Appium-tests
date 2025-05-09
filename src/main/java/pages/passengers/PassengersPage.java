package pages.passengers;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.appium.SelenideAppiumCollection;
import lombok.Getter;
import pages.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static com.codeborne.selenide.appium.SelenideAppium.$$;
import static io.appium.java_client.AppiumBy.id;
import static org.awaitility.Awaitility.await;

@Getter
public class PassengersPage extends BasePage {
    private final SelenideElement addPassengerButton = $(id("by.rw.client:id/btn_add_new_passenger"));
    private final SelenideElement listPassengers = $(id("by.rw.client:id/rv_passengers_list"));
    private final SelenideElement cyrillicButton = $(id("by.rw.client:id/btn_cyrillic"));
    private final SelenideElement dialogRemovePassengerOkButton = $(id("by.rw.client:id/dialog_ok"));
    private final SelenideAppiumCollection passengerNames = $$(id("by.rw.client:id/passenger_name"));
    private final SelenideAppiumCollection passengerDocumentNumbers = $$(id("by.rw.client:id/tv_passenger_document_number"));
    private final SelenideAppiumCollection removePassengerButtons = $$(id("by.rw.client:id/iv_action_with_passenger"));

    public PassengersPage removeAllPassengers() {
        await()
                .atMost(Duration.ofSeconds(120))
                .pollInterval(Duration.ofSeconds(1))
                .pollInSameThread()
                .until(() -> {
                    if (removePassengerButtons.isEmpty()) {
                        return true;
                    }
                    removePassengerButtons.first().click();
                    dialogRemovePassengerOkButton.click();
                    return false;
                });
        return this;
    }

}