package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private SelenideElement debitButton = $$(".button").findBy(Condition.exactText("Купить"));
    private SelenideElement creditButton = $$(".button").findBy(Condition.exactText("Купить в кредит"));


    public PurchasePage debitPurchase() {
        debitButton.click();
        return new PurchasePage(true);
    }

    public PurchasePage creditPurchase() {
        creditButton.click();
        return new PurchasePage(false);
    }
}
