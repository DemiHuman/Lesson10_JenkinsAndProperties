package tests;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.RegPage;
import utils.RandomUtils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Feature("Issue")
@Owner("eropkinpyu")
@Tag("different_tests")
public class TestRegistrationForm extends TestBase {


    RegPage regPage = new RegPage();
    RandomUtils getPhone = new RandomUtils();
    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            mobile = getPhone.getRandomIntString(10),
            gender = "Other",
            day = "30",
            month = "July",
            year = "1997",
            subject1 = "Maths",
            subject2 = "English",
            currentAddress = faker.address().fullAddress(),
            state = "Haryana",
            city = "Karnal",
            testPageUrl = "https://demoqa.com/automation-practice-form",
            gitHubLoginFormUrl = "https://github.com/login",
            loginGitHub = "login",
            pasGitHub = "password",
            googleUrl = "https://google.ru";

    String[] hobby = new String[]{"Sports", "Reading", "Music"};

    @Test
    @Story("Testing the student registration form")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "automation-practice-form", url = "https://demoqa.com/automation-practice-form")
    @DisplayName("Testing the student registration form")
    void testRegistrationFormWithSteps() {

        step("Открывает страницу с формой регистрации студента", (s) -> {
            s.parameter("url", testPageUrl);
            open(testPageUrl);
            $(".practice-form-wrapper").shouldHave(
                    text("Student Registration Form"));
        });
        step("Заполнение формы регистрации", () -> {
            step("Заполняем основные поля", (s) -> {
                s.parameter("Имя", firstName);
                s.parameter("Фамилия", lastName);
                s.parameter("Электронная почта", email);
                s.parameter("Пол", gender);
                s.parameter("Номер телефона", mobile);
                regPage.addFirstName(firstName);
                regPage.addLastName(lastName);
                regPage.addEmail(email);
                regPage.selectGender(gender);
                regPage.addPhone(mobile);
            });
            step("Заполняем дату рождения", (s) -> {
                s.parameter("Дата рождения", day + ", " + month + ", " + year);
                regPage.setBirthDay(day, month, year);
            });
            step("Выбираем предметы для изучения", (s) -> {
                s.parameter("Первый предмет", subject1);
                s.parameter("Первый предмет", subject2);
                regPage.addSubject(subject1);
                regPage.addSubject(subject2);
            });
            step("Выбираем хобби", () -> {
                regPage.selectHobby(hobby[0]);
                regPage.selectHobby(hobby[1]);
                regPage.selectHobby(hobby[2]);
            });
            if ($("#fixedban").exists()) {
                step("Убираем баннер с рекламой от google... (>_<)", () -> {
                    Selenide.executeJavaScript
                            ("let div = document.getElementById('fixedban');" +
                                    "div.remove();");
                });
            }
            step("Заполняем адрес", (s) -> {
                s.parameter("адрес", currentAddress);
                regPage.addAddress(currentAddress);
            });
            step("Указываем штат и город", (s) -> {
                s.parameter("Штат", state);
                s.parameter("Город", city);
                regPage.selectState(state);
                regPage.selectCity(city);
            });
            step("Нажимаем кнопку подтверждения", () -> {
                $("#submit").click();
            });
        });

        step("Проверка введённых данных на появившимся модальном окне", () ->

        {
            step("Проверяем ранее введёные данные", () -> {
                //Checking_After_The_Filling_Reg_Form
                $("#example-modal-sizes-title-lg").shouldHave(
                        text("Thanks for submitting the form"));
                $(".table-responsive").shouldHave(
                        text(firstName + " " + lastName),
                        text(email),
                        text("Other"),
                        text(mobile),
                        text(day + " " + month + "," + year),
                        text(subject1 + ", " + subject2),
                        text(hobby[0] + ", " + hobby[1] + ", " + hobby[2]),
                        text(currentAddress),
                        text(state + " " + city)
                );
            });
            step("Закрываем и проверяем отсудствие модального окна с данными формы регистрации", () -> {
                $("#closeLargeModal").click();
                $(".modal-content").shouldBe(disappear);
            });
        });
    }

    @Test
    @Story("Testing Github Sign In")
    @Severity(SeverityLevel.TRIVIAL)
    @Link(value = "githubLogin", url = "https://github.com/login")
    @DisplayName("Testing Github Sign In")
    void testGithubSignIn() {

        step("Open LoginForm", (s) -> {
            s.parameter("LoginForm", gitHubLoginFormUrl);
            open(gitHubLoginFormUrl);
        });

        step("Input login, password and click button \"Sign in\"", () -> {
            $("#login_field").val(loginGitHub);
            $("#password").val(pasGitHub);
            $(".btn").click();
        });

        step("Check login", () -> {
            $(".dropdown-caret", 1).click();
            $(".user-profile-link").shouldHave(text("Signed in as"));
        });
    }

    @Test
    @Story("Test \"Google search\"")
    @Severity(SeverityLevel.MINOR)
    @Link(value = "google", url = "https://google.ru")
    @DisplayName("Test \"Google search\"")
    void testOpenGoogle() {

        step("Open Google", (s) -> {
            s.parameter("google.ru", googleUrl);
            open(googleUrl);
        });

        step("Searching", () -> {
            $(By.name("q")).val("test").pressEnter();
        });
    }
}