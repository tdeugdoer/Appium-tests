package ui.main;

import components.AndroidPermissions;
import listeners.AndroidUIListener;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.main.MainPage;
import pages.searchStation.StationSearchPage;
import pages.timetable.TrainTimetablePage;
import pages.welcome.WelcomePage;
import ui.BaseTest;
import utils.DateUtils;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static io.qameta.allure.Allure.step;

@Listeners(AndroidUIListener.class)
public class MainPageTest extends BaseTest {
    private final WelcomePage welcomePage = new WelcomePage();
    private final MainPage mainPage = new MainPage();
    private final AndroidPermissions androidPermissions = new AndroidPermissions();
    private final StationSearchPage stationSearchPage = new StationSearchPage();
    private final TrainTimetablePage trainTimetablePage = new TrainTimetablePage();

    private String selectedDate;
    private List<String> fromTime;
    private List<String> toTime;
    private List<String> fromStation;
    private List<String> toStation;
    private List<String> trainNumber;
    private List<String> trainName;

    @Test(description = "Найти билеты Минск - Дрогичин-Город на завтрашний день")
    public void findTickets() {
        step("Переход к главной странице без регистрации", () -> welcomePage.getContinueWithoutRegisterButton().click());
        step("Ознакомление с информацией", mainPage::okAllInfoMessages);
        step("Соглашения Android об доступе к местоположению и уведомлениям", () -> {
            androidPermissions.clickIfVisible(androidPermissions.getAllowForegroundLocationTrackingButton());
            androidPermissions.clickIfVisible(androidPermissions.getAllowButton());
        });
        step("Заполнение данных для поиска поезда", () -> {
            step("Выбор станции отправления", () -> {
                mainPage.getDepartureStationField().click();
                stationSearchPage.getMinskPassazhirskyButton().click();
            });
            step("Выбор станции прибытия", () -> {
                mainPage.getDestinationStationField().click();
                stationSearchPage.fillInField(stationSearchPage.getSearchField(), "Дрогичин-го");
                stationSearchPage.getDrogichinTownButton().click();
            });
            step("Выбор даты", () -> mainPage.getTomorrowButton().click());
        });
        step("Поиск поезда", () -> mainPage.getFindButton().click());
        step("Получение данных о поездке", () -> {
            selectedDate = trainTimetablePage.getSelectedDate().getText();
            fromTime = trainTimetablePage.getFromTime().shouldBe(sizeGreaterThan(0), Duration.ofMinutes(2)).texts();
            toTime = trainTimetablePage.getToTime().shouldBe(sizeGreaterThan(0), Duration.ofMinutes(2)).texts();
            fromStation = trainTimetablePage.getFromStation().shouldBe(sizeGreaterThan(0), Duration.ofMinutes(2)).texts();
            toStation = trainTimetablePage.getToStation().shouldBe(sizeGreaterThan(0), Duration.ofMinutes(2)).texts();
            trainNumber = trainTimetablePage.getTrainNumber().shouldBe(sizeGreaterThan(0), Duration.ofMinutes(2)).texts();
            trainName = trainTimetablePage.getTrainName().shouldBe(sizeGreaterThan(0), Duration.ofMinutes(2)).texts();
        });
        step("Проверка полученных значений", () -> SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(selectedDate)
                    .as("Проверка даты, для которой отображается расписание")
                    .contains(DateUtils.getTomorrowDateString());

            softly.assertThat(fromTime)
                    .as("Проверка времени отправления")
                    .contains("22:57");

            softly.assertThat(toTime)
                    .as("Проверка времени прибытия")
                    .contains("05:51");

            softly.assertThat(fromStation)
                    .as("Проверка станции отправления")
                    .contains("Минск-Пассажирский");

            softly.assertThat(toStation)
                    .as("Проверка станции прибытия")
                    .contains("Дрогичин-Город");

            softly.assertThat(trainNumber)
                    .as("Проверка номера поезда")
                    .contains("657Б");

            softly.assertThat(trainName)
                    .as("Проверка названия поезда")
                    .contains("Полоцк — Брест-Центральный");
        }));
    }

}
