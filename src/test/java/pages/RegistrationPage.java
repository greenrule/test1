package pages;
// домашка 2
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    //components
    CalendarComponent calendarComponent = new CalendarComponent();

    //locators
    private SelenideElement

    headerTitle = $(".main-header"),
    firstNameInput = $("#firstName"),
    lastNameInput = $("#lastName"),
    userEmailInput = $("#userEmail"),
    userNumberInput = $("#userNumber"),
    subjectsTextInput = $("#subjectsInput"),
    currentAddressInput =  $("#currentAddress"),
    resultsTable = $(".table-responsive"),
    dateOfBirthInput = $("#dateOfBirthInput"),
    genderButton = $(byText("Male")),
    sportButton = $(byText("Sports")),
    selectState = $(byText("Select State")),
    selectCity= $(byText("Select City")),
    uploadPictureInput = $("#uploadPicture"),
    submitButton = $("#submit");


    //actions
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        headerTitle.shouldHave(text("Practice Form"));
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }
    public RegistrationPage setUserEmail(String userEmail) {
        userEmailInput.setValue(userEmail);
        return this;
    }
    public RegistrationPage setUserNumber(String userNumber) {
        userNumberInput.setValue(userNumber);
        return this;
    }
    public RegistrationPage setSubjectsInput(String subjectsText) {
        subjectsTextInput.setValue(subjectsText);
        return this;
    }
    public RegistrationPage setCurrentAddress(String currentAddress) {
        currentAddressInput.setValue(currentAddress);
        return this;
    }
    public void setBirthDay(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDay(day, month, year);
    }

    public RegistrationPage genderClick() {
        genderButton.click();
        return this;
    }

    public RegistrationPage sportsClick() {
        sportButton.click();
        return this;
    }

    public RegistrationPage uploadPicture(String picturePath) {
        uploadPictureInput.uploadFromClasspath(picturePath);
        return this;
    }

    public RegistrationPage selectState(String state) {
        selectState.click();
        $(byText(state)).click();
        return this;
    }

    public RegistrationPage selectCity(String city) {
        selectCity.click();
        $(byText(city)).click();
        return this;
    }

    public RegistrationPage submitButtonClick() {
        submitButton.click();
        return this;
    }


    public RegistrationPage checkForm(String fieldName, String value) {
        resultsTable.$(byText(fieldName)).parent().shouldHave(text(value));
        return this;
    }
}
