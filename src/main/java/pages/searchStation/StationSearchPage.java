package pages.searchStation;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import pages.BasePage;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static io.appium.java_client.AppiumBy.id;

@Getter
public class StationSearchPage extends BasePage {
    private final SelenideElement searchField = $(id("by.rw.client:id/search_src_text"));
    private final SelenideElement MinskPassazhirskyButton = $(androidUIAutomator("text(\"Минск-Пассажирский\")"));
    private final SelenideElement DrogichinTownButton = $(androidUIAutomator("text(\"Дрогичин-Город\")"));

}
