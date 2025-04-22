package pages.timetable;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import pages.BasePage;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static com.codeborne.selenide.appium.SelenideAppium.$x;
import static io.appium.java_client.AppiumBy.id;

@Getter
public class TrainTimetablePage extends BasePage {
    private final SelenideElement selectedDate = $x("//*[@resource-id='by.rw.client:id/tab_layout']//android.widget.TextView[@selected='true']");
    private final SelenideElement fromTime = $(id("by.rw.client:id/tv_timetable_item_from_time"));
    private final SelenideElement toTime = $(id("by.rw.client:id/tv_timetable_item_to_time"));
    private final SelenideElement fromStation = $(id("by.rw.client:id/tv_timetable_item_from_station_name"));
    private final SelenideElement toStation = $(id("by.rw.client:id/tv_timetable_item_to_station_name"));
    private final SelenideElement trainNumber = $(id("by.rw.client:id/tv_timetable_item_train_number"));
    private final SelenideElement trainName = $(id("by.rw.client:id/tv_timetable_item_train_name"));

}
