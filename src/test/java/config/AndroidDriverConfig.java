package config;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.net.URI;
import java.time.Duration;

public class AndroidDriverConfig implements WebDriverProvider {
    @NonNull
    @Override
    public WebDriver createDriver(@NonNull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("Medium Phone API 36")
                .setAppPackage("by.rw.client")
                .setAppActivity("by.rw.client/by.iba.railwayclient.presentation.signup.WelcomeActivity")
                .setAutomationName("UiAutomator2")
                .setNewCommandTimeout(Duration.ofSeconds(60))
                .setAutoGrantPermissions(true)
                .setNoReset(false)
                .setPlatformName("Android");
        try {
            return new AndroidDriver(
                    URI.create("http://127.0.0.1:4723").toURL(),
                    options
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to create AndroidDriver", e);
        }
    }

}

