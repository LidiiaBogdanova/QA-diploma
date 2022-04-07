package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PurchasePage {
    private SelenideElement heading;
    private SelenideElement cardNumber;
    private SelenideElement month;
    private SelenideElement year;
    private SelenideElement cardHolder;
    private SelenideElement cvv;
    private SelenideElement button;

    public PurchasePage(boolean isDebit)
    {
        this();
        String headingText;
        if(isDebit)
            headingText = "Оплата по карте";
        else
            headingText = "Кредит по данным карты";

        heading = $(Selectors.withText(headingText));
        heading.shouldBe(visible);
    }

    private PurchasePage()
    {
         cardNumber = $("[placeholder = '0000 0000 0000 0000']");
         month = $("[placeholder = '08']");
         year = $("[placeholder='22']");
         cardHolder = $("#root div:nth-child(3) span span:nth-child(1) span span span.input__box input");
         cvv = $("[placeholder='999']");
         button = $$(".button").findBy(Condition.exactText("Продолжить"));
    }

    public void inputData(String number, String monthData, String yearData, String holder, String cvc) {
        cardNumber.setValue(number);
        month.setValue(monthData);
        year.setValue(yearData);
        cardHolder.setValue(holder);
        cvv.setValue(cvc);
        button.click();
    }

    public void checkIncorrectCvvNotification() {
        $("#root div:nth-child(3) span:nth-child(2)  span.input__sub").shouldHave(exactText("Неверный формат")).shouldBe(visible);
    }

    public void checkIncorrectNameHolderNotification() {
        $("#root  span  span:nth-child(1)  span  span  span.input__sub").shouldHave(exactText("Неверный формат")).shouldBe(visible);
    }

    public void checkIncorrectMonthNotification() {
        $("#root div:nth-child(2) span.input__sub").shouldHave(exactText("Неверный формат")).shouldBe(visible);
    }

    public void checkInvalidYearNotification() {
        $("#root div:nth-child(2)  span:nth-child(2)    span.input__sub").shouldHave(exactText("Истёк срок действия карты")).shouldBe(visible);
    }

    public void checkIncorrectYearNotification() {
        $("#root div:nth-child(2)  span:nth-child(2)    span.input__sub").shouldHave(exactText("Неверный формат")).shouldBe(visible);
    }

    public void checkIncorrectCardNumberNotification() {
        $("#root fieldset div:nth-child(1) span.input__sub").shouldHave(exactText("Неверный формат")).shouldBe(visible);
    }

    public void checkEmptyNameHolderNotification() {
        $("#root  span  span:nth-child(1)  span  span  span.input__sub").shouldHave(exactText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    public void checkSuccessPurchaseNotification() {
        $(".notification_status_ok").shouldBe(visible, Duration.ofSeconds(20));
    }

    public void checkFailedPurchaseNotification() {
        $(".notification_status_error").shouldBe(visible, Duration.ofSeconds(20));
    }

    public void checkNoSuccessPurchaseNotification() {
        $(".notification_status_ok").shouldNotBe(visible, Duration.ofSeconds(20));
    }

    public void checkNoFailedPurchaseNotification() {

        $(".notification_status_error").shouldNotBe(visible, Duration.ofSeconds(20));
    }

    public void checkCardNumberFieldIsEmpty() {
        cardNumber.shouldBe(empty);
    }

    public void checkMonthFieldIsEmpty() {
        month.shouldBe(empty);
    }

    public void checkYearFieldIsEmpty() {
        year.shouldBe(empty);
    }

    public void checkCvvFieldIsEmpty() {
        cvv.shouldBe(empty);
    }


}
