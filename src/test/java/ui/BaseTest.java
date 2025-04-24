package ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.appium.SelenideAppium;
import org.testng.annotations.AfterMethod;


public class BaseTest {
    @AfterMethod(description = "Перезапуск приложения", alwaysRun = true)
    public void cleanup() {
        Selenide.closeWebDriver();
        SelenideAppium.launchApp();
    }

}
