package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.TestResultComponent;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderWraper = $("#genterWrapper"),
            userPhoneNumberInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            setHobbies = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            selectState = $("#state"),
            selectCity = $("#city"),
            submitButton = $("#submit"),
            redGenderWraper = $("#genterWrapper .custom-control-label");




    CalendarComponent calendarComponent = new CalendarComponent();
    TestResultComponent testResultComponent = new TestResultComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationPage closeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationPage setName(String name) {
        firstNameInput.setValue(name);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public RegistrationPage setGender(String gender) {
        genderWraper.$(byText(gender)).click();
        return this;
    }

    public RegistrationPage setUserPhoneNumberInput(String number) {
        userPhoneNumberInput.setValue(number);
        return this;
    }

    public RegistrationPage setBirthday(String year, String month, String day) {
        dateOfBirthInput.click();
        calendarComponent.setDate(year, month, day);
        return this;
    }

    public RegistrationPage setSubjects(String subjects) {
        subjectsInput.setValue(subjects).pressEnter();
        return this;
    }

    public RegistrationPage setHobbies(String hobbies) {
        setHobbies.$(byText(hobbies)).click();
        return this;
    }

    public RegistrationPage uploadFile(String picture) {
        uploadPicture.uploadFromClasspath(picture);
        return this;
    }

    public RegistrationPage setAddress(String address) {
        addressInput.setValue(address);
        return this;
    }

    public RegistrationPage setState(String state) {
        selectState.click();
        selectState.$(byText(state)).click();
        return this;
    }

    public RegistrationPage setCity(String city) {
        selectCity.click();
        selectCity.$(byText(city)).click();
        return this;
    }

    public RegistrationPage submitForm() {
        submitButton.click();
        return this;
    }
    public RegistrationPage checkResult(String key, String value) {
        testResultComponent.checkTable(key, value);
       return this;
    }


    public void checkFillFormWithoutGender() {
        redGenderWraper.shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
    }
}



