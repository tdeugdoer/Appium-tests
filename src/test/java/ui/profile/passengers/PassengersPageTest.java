package ui.profile.passengers;

import components.AndroidPermissions;
import components.Navigation;
import formData.PassengerFormData;
import listeners.AndroidUIListener;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.auth.LoginPage;
import pages.main.MainPage;
import pages.passengers.AdditionPassengerPage;
import pages.passengers.PassengersPage;
import pages.profile.ProfilePage;
import pages.welcome.WelcomePage;
import ui.BaseTest;
import utils.data.LoginData;
import utils.data.PassengerData;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static io.qameta.allure.Allure.step;

@Listeners(AndroidUIListener.class)
public class PassengersPageTest extends BaseTest {
    private final WelcomePage welcomePage = new WelcomePage();
    private final MainPage mainPage = new MainPage();
    private final AndroidPermissions androidPermissions = new AndroidPermissions();
    private final Navigation navigation = new Navigation();
    private final ProfilePage profilePage = new ProfilePage();
    private final LoginPage loginPage = new LoginPage();
    private final PassengersPage passengersPage = new PassengersPage();
    private final AdditionPassengerPage additionPassengerPage = new AdditionPassengerPage();

    private List<String> passengerNames;
    private List<String> passengerDocumentNumbers;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        step("Переход к странице аутентификации", () -> welcomePage.getLoginButton().click());
        step("Аутентификация", () -> {
            loginPage.fillInField(loginPage.getLoginField(), LoginData.EXISTING_EMAIL);
            loginPage.fillInField(loginPage.getPasswordField(), LoginData.EXISTING_PASSWORD);
            loginPage.getLoginButton().click();
        });
        step("Ознакомление с информацией", mainPage::okAllInfoMessages);
        step("Соглашения Android об доступе к местоположению и уведомлениям", () -> {
            androidPermissions.clickIfVisible(androidPermissions.getAllowForegroundLocationTrackingButton());
            androidPermissions.clickIfVisible(androidPermissions.getAllowButton());
        });
        step("Перейти на страницу пассажиров", () -> {
            navigation.getProfile().click();
            profilePage.getPassengersButton().click();
        });
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        step("Перейти на начальную страницу пассажиров", () -> {
            navigation.getOrders().click();
            navigation.getProfile().click();
            profilePage.getPassengersButton().click();
        });
        step("Удаление всех пассажиров", () -> {
            passengersPage.removeAllPassengers();
        });
    }

    @Test(description = "Добавление пассажира")
    public void additionPassenger() {
        step("Добавление пассажира", () -> {
            passengersPage.getAddPassengerButton().click();
            passengersPage.getCyrillicButton().click();
            step("Заполнение данных пассажира", () -> additionPassengerPage
                    .fillOutForm(getPassengerFormData())
                    .getSaveButton().click());
        });
        step("Получение данных о поездке", () -> {
            passengerNames = passengersPage.getPassengerNames().shouldBe(sizeGreaterThan(0), Duration.ofMinutes(1)).texts();
            passengerDocumentNumbers = passengersPage.getPassengerDocumentNumbers().shouldBe(sizeGreaterThan(0), Duration.ofMinutes(1)).texts();
        });
        step("Проверка полученных значений", () -> SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(passengerNames)
                    .as("Проверка появления имени нового пассажира")
                    .contains(PassengerData.SURNAME + " " + PassengerData.NAME + " " + PassengerData.PATRONYMIC);

            softly.assertThat(passengerDocumentNumbers)
                    .as("Проверка появления номера документа нового пассажира")
                    .contains(PassengerData.DOCUMENT_NUMBER);
        }));
    }

    private PassengerFormData getPassengerFormData() {
        return PassengerFormData.builder()
                .surname(PassengerData.SURNAME)
                .name(PassengerData.NAME)
                .patronymic(PassengerData.PATRONYMIC)
                .birthday(PassengerData.BIRTHDAY)
                .documentNumber(PassengerData.DOCUMENT_NUMBER)
                .build();
    }

}
