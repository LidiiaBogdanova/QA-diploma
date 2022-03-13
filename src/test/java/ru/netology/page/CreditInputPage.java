package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CreditInputPage {

    private SelenideElement creditHeading = $("heading").shouldHave(Condition.text("Кредит по данным карты"));
    private SelenideElement cardNumber= $("input__top").shouldHave(Condition.exactText("Номер карты"));
    private SelenideElement month= $("input__top").shouldHave(Condition.exactText("Месяц"));
    private SelenideElement year= $("input__top").shouldHave(Condition.exactText("Год"));
    private SelenideElement cardHolder= $("input__top").shouldHave(Condition.exactText("Владелец"));
    private SelenideElement cvv= $("input__top").shouldHave(Condition.exactText("CVC/CVV"));
    private SelenideElement button=$("button").shouldHave(text("Продолжить"));



    public CreditInputPage() {
        creditHeading.shouldBe(visible);
    }

    public void correctDataInput(){
        cardNumber.setValue(DataHelper.getCorrectCardInfo().getCardNumber());
        month.setValue(DataHelper.getCorrectCardInfo().getMonth());
        year.setValue(DataHelper.getCorrectCardInfo().getYear());
        cardHolder.setValue(DataHelper.getCorrectCardInfo().getCardHolder());
        cvv.setValue(DataHelper.getCorrectCardInfo().getCvv());
    }




}
