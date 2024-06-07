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
            userPhoneNumber = $("#userNumber"),
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
    private HashMap<String, String> userForm = new HashMap();

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
        userForm.put("Student Name", name + " " + lastName);
        return this;
    }

    public RegistrationPage setEmail(String email) {
        emailInput.setValue(email);
        userForm.put("Student Email", email);
        return this;
    }

    public RegistrationPage setGender(String gender) {
        genderWraper.$(byText(gender)).click();
        userForm.put("Gender", gender);
        return this;
    }

    public RegistrationPage setUserPhoneNumber(String number) {
        userPhoneNumber.setValue(number);
        userForm.put("Mobile", number);
        return this;
    }

    public RegistrationPage setBirthday(String year, String month, String day) {
        dateOfBirthInput.click();
        calendarComponents.setDate(year, month, day);
        userForm.put("Date of Birth", day + " " + month + "," + year);
        return this;
    }

    public RegistrationPage setSubjects(String subjects) {
        subjectsInput.setValue(subjects).pressEnter();
        userForm.put("Subjects", subjects);
        return this;
    }

    public RegistrationPage setHobbies(String hobbies) {
        setHobbies.$(byText(hobbies)).click();
        userForm.put("Hobbies", hobbies);
        return this;
    }

    public RegistrationPage uploadFile(String picture) {
        uploadPicture.uploadFromClasspath(picture);
        userForm.put("Picture", picture);
        return this;
    }

    public RegistrationPage setAddress(String address) {
        addressInput.setValue(address);
        userForm.put("Address", address);
        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city) {
        selectState.setValue(state).pressEnter();
        selectCity.setValue(city).pressEnter();
        userForm.put("State and City", state);
        return this;
    }

    public RegistrationPage submitForm() {
        submitButton.click();
        return this;
    }

    public void checkResults() {

        for (String key : userForm.keySet()) {
            String value = userForm.get(key);
            resultTable.$(byText(key)).parent().shouldHave(text(value));
        }
    }

    public void checkFillFormWithoutGender() {
        redGenderWraper.shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
    }
}



