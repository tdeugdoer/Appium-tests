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
import utils.DateUtils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Listeners(AndroidUIListener.class)
public class MainPageTest {
    private final WelcomePage welcomePage = new WelcomePage();
    private final MainPage mainPage = new MainPage();
    private final AndroidPermissions androidPermissions = new AndroidPermissions();
    private final StationSearchPage stationSearchPage = new StationSearchPage();
    private final TrainTimetablePage trainTimetablePage = new TrainTimetablePage();

    private String selectedDate;
    private String fromTime;
    private String toTime;
    private String fromStation;
    private String toStation;
    private String trainNumber;
    private String trainName;

    @Test(description = "Найти билеты Минск - Дрогичин-Город на завтрашний день")
    public void findTickets() {
        step("Переход к главной странице без регистрации", () -> welcomePage.getContinueWithoutRegisterButton().click());
        step("Ознакомление с информацией", () ->
                mainPage.getInfoMessageOkButton()
                        .shouldBe(visible, Duration.ofSeconds(10))
                        .click()
        );
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
            selectedDate = trainTimetablePage.getSelectedDate().shouldBe(exist, Duration.ofSeconds(60)).getText();
            fromTime = trainTimetablePage.getFromTime().getText();
            toTime = trainTimetablePage.getToTime().getText();
            fromStation = trainTimetablePage.getFromStation().getText();
            toStation = trainTimetablePage.getToStation().getText();
            trainNumber = trainTimetablePage.getTrainNumber().getText();
            trainName = trainTimetablePage.getTrainName().getText();
        });
        step("Проверка полученных значений", () -> {
//            String selectedDate = trainTimetablePage.getSelectedDate().shouldBe(exist, Duration.ofSeconds(60)).getText();
//            String fromTime = trainTimetablePage.getFromTime().getText();
//            String toTime = trainTimetablePage.getToTime().getText();
//            String fromStation = trainTimetablePage.getFromStation().getText();
//            String toStation = trainTimetablePage.getToStation().getText();
//            String trainNumber = trainTimetablePage.getTrainNumber().getText();
//            String trainName = trainTimetablePage.getTrainName().getText();

            SoftAssertions.assertSoftly(softly -> {
                assertThat(selectedDate)
                        .as("Проверка даты, для которой отображается расписание")
                        .contains(DateUtils.getTomorrowDateString());

                assertThat(fromTime)
                        .as("Проверка времени отправления")
                        .isEqualTo("22:57");

                assertThat(toTime)
                        .as("Проверка времени прибытия")
                        .isEqualTo("05:51");

                assertThat(fromStation)
                        .as("Проверка станции отправления")
                        .isEqualTo("Минск-Пассажирский");

                assertThat(toStation)
                        .as("Проверка станции прибытия")
                        .isEqualTo("Дрогичин-Город");

                assertThat(trainNumber)
                        .as("Проверка номера поезда")
                        .isEqualTo("657Б");

                assertThat(trainName)
                        .as("Проверка названия поезда")
                        .isEqualTo("Полоцк — Брест-Центральный");
            });
        });

    }

}
