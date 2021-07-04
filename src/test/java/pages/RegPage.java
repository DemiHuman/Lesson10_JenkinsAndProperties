package pages;

import com.codeborne.selenide.*;
import components.CalendarComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegPage {

    private SelenideElement firstNameInput = $("#firstName");
    private SelenideElement lastNameInput = $("#lastName");
    private SelenideElement emailInput = $("#userEmail");
    private SelenideElement genderRadio = $("#genterWrapper");
    private SelenideElement phoneInput = $("#userNumber");
    private SelenideElement subjectInput = $("#subjectsInput");
    private SelenideElement hobbySelect = $("#hobbiesWrapper");
    private SelenideElement addressInput = $("#currentAddress");
    private SelenideElement stateCityWrapper = $("#stateCity-wrapper");
    private SelenideElement stateSelect = $("#state");
    private SelenideElement citySelect = $("#city");

    public void addFirstName(String firstName) {
        firstNameInput.val(firstName);
    }

    public void addLastName(String lasttName) {
        lastNameInput.val(lasttName);
    }

    public void addEmail(String email) {
        emailInput.val(email);
    }

    public void setBirthDay(String day, String month, String year) {
        new CalendarComponent().setDate(day, month, year);
    }

    public void selectGender(String gender) {
        genderRadio.$(byText(gender)).click();
    }

    public void addPhone(String phone) {
        phoneInput.val(phone);
    }

    public void addSubject(String subject) {
        subjectInput.val(subject).pressEnter();
    }

    public void selectHobby(String hobby) {
        hobbySelect.$(byText(hobby)).click();
    }

    public void addAddress(String address) {
        addressInput.val(address);
    }

    public void selectState(String state) {
        stateSelect.click();
        stateCityWrapper.$(byText(state)).click();
    }

    public void selectCity(String city) {
        citySelect.click();
        stateCityWrapper.$(byText(city)).click();
    }
}
