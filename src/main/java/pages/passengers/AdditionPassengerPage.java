package pages.passengers;

import com.codeborne.selenide.SelenideElement;
import formData.PassengerFormData;
import lombok.Getter;
import pages.BasePage;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static io.appium.java_client.AppiumBy.id;

@Getter
public class AdditionPassengerPage extends BasePage {
    private final SelenideElement surnameField = $(id("by.rw.client:id/passenger_surname"));
    private final SelenideElement nameField = $(id("by.rw.client:id/passenger_name"));
    private final SelenideElement patronymicField = $(id("by.rw.client:id/passenger_patronymic"));
    private final SelenideElement birthdayField = $(id("by.rw.client:id/passenger_birthday"));
    private final SelenideElement documentTypeField = $(id("by.rw.client:id/passenger_document_type"));
    private final SelenideElement passportRBOption = $(androidUIAutomator("text(\"Паспорт гражданина Республики Беларусь\")"));
    private final SelenideElement documentNumberField = $(id("by.rw.client:id/passenger_document_number"));
    private final SelenideElement saveButton = $(id("by.rw.client:id/btn_passenger_save"));

    public AdditionPassengerPage fillOutForm(PassengerFormData formData) {
        fillInField(surnameField, formData.getSurname());
        fillInField(nameField, formData.getName());
        fillInField(patronymicField, formData.getPatronymic());
        fillInField(birthdayField, formData.getBirthday());
        documentTypeField.click();
        passportRBOption.click();
        fillInField(documentNumberField, formData.getDocumentNumber());
        return this;
    }

}
