package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DataInputPage {

    private SelenideElement debitHeading = $("heading").shouldHave(Condition.text("Оплата по карте"));
    private SelenideElement creditHeading = $("heading").shouldHave(Condition.text("Кредит по данным карты"));
    private SelenideElement cardNumber= $("input__top").shouldHave(Condition.exactText("Номер карты"));
    private SelenideElement month= $("input__top").shouldHave(Condition.exactText("Месяц"));
    private SelenideElement year= $("input__top").shouldHave(Condition.exactText("Год"));
    private SelenideElement cardHolder= $("input__top").shouldHave(Condition.exactText("Владелец"));
    private SelenideElement cvv= $("input__top").shouldHave(Condition.exactText("CVC/CVV"));







}
