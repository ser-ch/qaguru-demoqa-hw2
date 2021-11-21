package qa.demo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class StudentRegistrationFormTest {

    String firstName = "Rajesh";
    String secondName = "Koothrappali";
    String email = "r.koothrappali@mail.com";
    String gender = "Male";
    String phoneNumber = "1234567891";
    String subject = "Computer Science";
    String hobby = "Reading";
    String pictureName = "Rajesh_Koothrappali.jpg";
    String address = "A-10, A-10, Hari Nagar";
    String state = "NCR";
    String city = "Delhi";


    @BeforeEach
    void setUp() {
        Configuration.startMaximized = true;
    }

    @Test
    void registrationFormTest() {

        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(secondName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(phoneNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("October");
        $(".react-datepicker__year-select").selectOption("1985");
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)").findBy(text("6")).click();

        $("#subjectsInput").setValue(subject).pressEnter();
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#uploadPicture").uploadFromClasspath(pictureName);
        $("#currentAddress").setValue(address);

        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        $("#submit").click();

        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName + " " + secondName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(email));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(phoneNumber));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(subject));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(hobby));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text(pictureName));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(address));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text(state + " " + city));
    }
}
