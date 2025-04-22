package listeners;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.appium.SelenideAppium;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.AndroidDriverConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.ITestContext;
import org.testng.ITestListener;

public class AndroidUIListener implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        Configuration.browser = AndroidDriverConfig.class.getName();
        SelenideAppium.launchApp();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(Boolean.parseBoolean(System.getProperty("allure_screenshots", Boolean.TRUE.toString())))
                .savePageSource(Boolean.parseBoolean(System.getProperty("allure_page_sources", Boolean.TRUE.toString())))
        );
    }

}
