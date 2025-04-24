package pages.timetable;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.appium.SelenideAppiumCollection;
import lombok.Getter;
import pages.BasePage;

import static com.codeborne.selenide.appium.SelenideAppium.$$;
import static com.codeborne.selenide.appium.SelenideAppium.$x;
import static io.appium.java_client.AppiumBy.id;

@Getter
public class TrainTimetablePage extends BasePage {
    private final SelenideElement selectedDate = $x("//*[@resource-id='by.rw.client:id/tab_layout']//android.widget.TextView[@selected='true']");
    private final SelenideAppiumCollection fromTime = $$(id("by.rw.client:id/tv_timetable_item_from_time"));
    private final SelenideAppiumCollection toTime = $$(id("by.rw.client:id/tv_timetable_item_to_time"));
    private final SelenideAppiumCollection fromStation = $$(id("by.rw.client:id/tv_timetable_item_from_station_name"));
    private final SelenideAppiumCollection toStation = $$(id("by.rw.client:id/tv_timetable_item_to_station_name"));
    private final SelenideAppiumCollection trainNumber = $$(id("by.rw.client:id/tv_timetable_item_train_number"));
    private final SelenideAppiumCollection trainName = $$(id("by.rw.client:id/tv_timetable_item_train_name"));

}
