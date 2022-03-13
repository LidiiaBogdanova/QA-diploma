package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private SelenideElement debitButton = $(".button").shouldHave(Condition.exactText("Купить"));
   // private SelenideElement creditButton = $(".button").shouldHave(Condition.exactText("Купить в кредит"));


    public DebitInputPage debitPurchase() {
        debitButton.click();
        return new DebitInputPage();
    }

//    public CreditInputPage creditPurchase() {
//        creditButton.click();
//        return new CreditInputPage();
//    }
}
