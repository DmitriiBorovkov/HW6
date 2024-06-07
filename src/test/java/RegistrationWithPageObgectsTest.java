import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import tests.TestBase;

public class RegistrationWithPageObgectsTest extends TestBase {
    @Test
    void fillFormTest() {
        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.openPage()
                .setNameAndLastname("Dmitrii", "Borovkov")
                .setEmail("test@test.com")
                .setGender("Male")
                .setUserPhoneNumber("7999888770")
                .setBirthday("1987", "February", "09")
                .setSubjects("Maths")
                .setHobbies("Sports")
                .uploadFile("unnamed.png")
                .setAddress("ul. Pushkina d. Kolotushkina")
                .setStateAndCity("NCR", "Delhi")
                .submitForm()

                .checkResults();
    }

    @Test
    void fillFormWithMinimumData() {
        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.openPage()
                .setNameAndLastname("Dmitrii", "Borovkov")
                .setGender("Male")
                .setUserPhoneNumber("7999888770")
                .setBirthday("1987", "February", "09")
                .submitForm()

                .checkResults();
    }

    @Test
    void fillFormNegativeTest() {
        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.openPage()
                .setNameAndLastname("Dmitrii", "Borovkov")
              //.setGender("Male") - обязательное поле не заполнено
                .setUserPhoneNumber("7999888770")
                .setBirthday("1987", "February", "09")
                .submitForm()

                .checkFillFormWithoutGender();
    }
}
