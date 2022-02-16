package tests;
//домашка 2
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationFormWithPageObjectsTests {
    RegistrationPage registrationPage = new RegistrationPage();
    String firstName = "Alex";
    String lastName = "Ivanov";
    String userEmail ="Alex@test.com";
    String userNumber ="7904540014";
    String subjectsText ="888";
    String currentAddress = "999999";
    String state = "NCR";
    String city = "Delhi";
    String picturePath = "img/1.jpg";
    String testDay = "30";
    String testMonth = "July";
    String testYear = "2008";
    String gender = "Male";
    String hobbies = "Sports";
    String url = "1.jpg";

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void successFillTest() {

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .chooseGender(gender)
                .setUserNumber(userNumber);
        registrationPage.setBirthDay(testDay, testMonth, testYear);
        registrationPage.setSubjectsInput(subjectsText)
                .chooseHobbies(hobbies)
                .uploadPicture(picturePath)
                .setCurrentAddress(currentAddress)
                .selectState(state)
                .selectCity(city)
                .submitButtonClick();


        registrationPage.checkForm("Student Name", firstName + " "+ lastName)
                        .checkForm("Student Email", userEmail)
                        .checkForm("Gender", gender)
                        .checkForm("Mobile", userNumber)
                        .checkForm("Date of Birth", testDay + " "+testMonth + "," +testYear)
                        .checkForm("Hobbies", hobbies)
                        .checkForm("Picture", url)
                        .checkForm("Address", currentAddress)
                        .checkForm("State and City", state + " "+ city);

    }
}
