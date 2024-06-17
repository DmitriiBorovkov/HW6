package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.components.TestResultComponent;


public class RegistrationWithPageObjectsTest extends TestBase {
    @Test
    void fillFormTest() {
        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.openPage()
                .closeBanner()
                .setName("Dmitrii")
                .setLastName("Borovkov")
                .setEmail("test@test.com")
                .setGender("Male")
                .setUserPhoneNumberInput("7999888770")
                .setBirthday("1987", "February", "09")
                .setSubjects("Maths")
                .setHobbies("Sports")
                .uploadFile("unnamed.png")
                .setAddress("ul. Pushkina d. Kolotushkina")
                .setState("NCR")
                .setCity("Delhi")
                .submitForm()

                .checkResult("Student Name", "Dmitrii Borovkov")
                .checkResult("Student Email", "test@test.com")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "7999888770")
                .checkResult("Date of Birth", "09 February,1987")
                .checkResult("Subjects", "Maths")
                .checkResult("Hobbies", "Sports")
                .checkResult("Picture", "unnamed.PNG")
                .checkResult("Address", "ul. Pushkina d. Kolotushkina")
                .checkResult("State and City", "NCR Delhi");


    }

    @Test
    void fillFormWithMinimumData() {
        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.openPage()
                .setName("Dmitrii")
                .setLastName("Borovkov")
                .setGender("Male")
                .setUserPhoneNumberInput("7999888770")
                .setBirthday("1987", "February", "09")
                .submitForm()

                .checkResult("Student Name", "Dmitrii Borovkov")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "7999888770")
                .checkResult("Date of Birth", "09 February,1987");

    }

    @Test
    void fillFormNegativeTest() {
        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.openPage()
                .setName("Dmitrii")
                .setLastName("Borovkov")
              //.setGender("Male") - обязательное поле не заполнено
                .setUserPhoneNumberInput("7999888770")
                .setBirthday("1987", "February", "09")
                .submitForm()

                .checkFillFormWithoutGender();
    }
}
