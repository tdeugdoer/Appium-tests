package pages.main;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import pages.BasePage;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static io.appium.java_client.AppiumBy.id;

@Getter
public class MainPage extends BasePage {
    private final SelenideElement infoMessageOkButton = $(id("by.rw.client:id/button_ok"));
    private final SelenideElement departureStationField = $(id("by.rw.client:id/departure_station_selection_container"));
    private final SelenideElement destinationStationField = $(id("by.rw.client:id/destination_station_selection_container"));
    private final SelenideElement tomorrowButton = $(androidUIAutomator("text(\"Завтра\")"));
    private final SelenideElement findButton = $(id("by.rw.client:id/btn_find_timetable"));

}
