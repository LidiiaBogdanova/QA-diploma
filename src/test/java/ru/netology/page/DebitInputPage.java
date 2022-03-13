package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DebitInputPage {

    private SelenideElement debitHeading = $(Selectors.withText("Оплата по карте"));
    private SelenideElement cardNumber= $("[placeholder = '0000 0000 0000 0000']");
    private SelenideElement month= $("[placeholder = '08']");
    private SelenideElement year= $("[placeholder='22']");
    private SelenideElement cardHolder= $("#root div:nth-child(3) span span:nth-child(1) span span span.input__box input");
    private SelenideElement cvv= $("[placeholder='999']");
    private SelenideElement button=$$(".button").findBy(Condition.exactText("Продолжить"));


    public DebitInputPage() {
        debitHeading.shouldBe(visible);
    }

    public void correctDataInput(String number, String monthData, String yearData, String holder, String cvc){
        cardNumber.setValue(number);
        month.setValue(monthData);
        year.setValue(yearData);
        cardHolder.setValue(holder);
        cvv.setValue(cvc);
        button.click();
    }

    public void IncorrectDataInput(String number, String monthData, String yearData, String holder, String cvc){
        cardNumber.setValue(number);
        month.setValue(monthData);
        year.setValue(yearData);
        cardHolder.setValue(holder);
        cvv.setValue(cvc);
        button.click();
    }

public void IncorrectCvvNotification(){
    $("#root div:nth-child(3) span:nth-child(2)  span.input__sub").shouldHave(exactText("Неверный формат")).shouldBe(visible);}

    public void IncorrectNameHolderNotification(){
        $("#root  span  span:nth-child(1)  span  span  span.input__sub").shouldHave(exactText("Неверный формат")).shouldBe(visible);    }

     public void IncorrectMonthNotification(){
        $("#root div:nth-child(2) span.input__sub").shouldHave(exactText("Неверный формат")).shouldBe(visible);    }

    public void InvalidYearNotification(){
        $("#root div:nth-child(2)  span:nth-child(2)    span.input__sub").shouldHave(exactText("Истёк срок действия карты")).shouldBe(visible);    }

    public void IncorrectYearNotification(){
        $("#root div:nth-child(2)  span:nth-child(2)    span.input__sub").shouldHave(exactText("Неверный формат")).shouldBe(visible);    }

    public void IncorrectCardNumberNotification(){
        $("#root fieldset div:nth-child(1) span.input__sub").shouldHave(exactText("Неверный формат")).shouldBe(visible);    }

    public void EmptyNameHolderNotification(){
        $("#root  span  span:nth-child(1)  span  span  span.input__sub").shouldHave(exactText("Поле обязательно для заполнения")).shouldBe(visible);    }

    public void ShowSuccessPurchaseNotification(){
        $(".notification_status_ok").shouldBe(visible, Duration.ofSeconds(20));    }

    public void ShowFailedPurchaseNotification(){
        $(".notification_status_error").shouldBe(visible, Duration.ofSeconds(20));    }

    public void NoShowSuccessPurchaseNotification(){
        $(".notification_status_ok").shouldNotBe(visible, Duration.ofSeconds(20));    }

    public void NoShowFailedPurchaseNotification() {

        $(".notification_status_error").shouldNotBe(visible, Duration.ofSeconds(20));}

    public void NoLettersInCardNumber(){
        cardNumber.shouldBe(empty);    }

    public void NoLettersInMonth(){
        month.shouldBe(empty);    }

    public void NoLettersInYear(){
        year.shouldBe(empty);    }

    public void NoLettersInCvv(){
        cvv.shouldBe(empty);    }







}
