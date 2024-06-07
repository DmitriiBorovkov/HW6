package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponents;

import java.util.HashMap;

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
            selectState = $("#react-select-3-input"),
            selectCity = $("#react-select-4-input"),
            submitButton = $("#submit"),
            redGenderWraper = $("#genterWrapper .custom-control-label"),

            resultTable = $(".modal-content");


    CalendarComponents calendarComponents = new CalendarComponents();
    private HashMap<String, String> userFormMap = new HashMap(); // Ключ - название поля в результрующей таблице.
                                                                 // Значение - данные, которые передавали в тесте

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationPage setNameAndLastname(String name, String lastName) {
        firstNameInput.setValue(name);
        lastNameInput.setValue(lastName);
        userFormMap.put("Student Name", name + " " + lastName);
        return this;
    }

    public RegistrationPage setEmail(String email) {
        emailInput.setValue(email);
        userFormMap.put("Student Email", email);
        return this;
    }

    public RegistrationPage setGender(String gender) {
        genderWraper.$(byText(gender)).click();
        userFormMap.put("Gender", gender);
        return this;
    }

    public RegistrationPage setUserPhoneNumberInput(String number) {
        userPhoneNumberInput.setValue(number);
        userFormMap.put("Mobile", number);
        return this;
    }

    public RegistrationPage setBirthday(String year, String month, String day) {
        dateOfBirthInput.click();
        calendarComponents.setDate(year, month, day);
        userFormMap.put("Date of Birth", day + " " + month + "," + year);
        return this;
    }

    public RegistrationPage setSubjects(String subjects) {
        subjectsInput.setValue(subjects).pressEnter();
        userFormMap.put("Subjects", subjects);
        return this;
    }

    public RegistrationPage setHobbies(String hobbies) {
        setHobbies.$(byText(hobbies)).click();
        userFormMap.put("Hobbies", hobbies);
        return this;
    }

    public RegistrationPage uploadFile(String picture) {
        uploadPicture.uploadFromClasspath(picture);
        userFormMap.put("Picture", picture);
        return this;
    }

    public RegistrationPage setAddress(String address) {
        addressInput.setValue(address);
        userFormMap.put("Address", address);
        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city) {
        selectState.setValue(state).pressEnter();
        selectCity.setValue(city).pressEnter();
        userFormMap.put("State and City", state);
        return this;
    }

    public RegistrationPage submitForm() {
        submitButton.click();
        return this;
    }

    public void checkResults() {

        for (String key : userFormMap.keySet()) {
            String value = userFormMap.get(key);
            resultTable.$(byText(key)).parent().shouldHave(text(value));
        }
    }

    public void checkFillFormWithoutGender() {
        redGenderWraper.shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
    }
}



