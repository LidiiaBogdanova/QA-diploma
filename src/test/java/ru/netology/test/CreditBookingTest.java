package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import jdk.jfr.Description;
import lombok.var;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DBHelper;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.PurchasePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CreditBookingTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @Test
    @Description("Проверка успешной покупки с одобренной картой, проверка ее статуса в разделе оплаты в БД")
    void shouldCreditBookWithCorrectCard() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkSuccessPurchaseNotification();
        var status = DBHelper.checkStatus();
        assertEquals("APPROVED", status);
    }

    @Test
    @Description("Проверка отклоненной покупки с неодобренной картой, проверка ее статуса в разделе оплаты в БД")
    void shouldNotDebitBookWithUnCorrectCard() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getIncorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkFailedPurchaseNotification();
        var status = DBHelper.checkStatus();
        assertEquals("DECLINED", status);
    }

    @Test
    @Description("Проверяется, вылезает ли при необходимости уведомление об некорректном годе окончания срока действия карты, если указан прошедший год")
    void shouldNotDebitBookWithInvalidYear() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), "20", data.getCardHolder(), data.getCvv());
        purchasePage.checkInvalidYearNotification();
    }

    @Test
    @Description("Проверяется, вылезает ли при необходимости уведомление об ошибке при опечатке в поле Владелец")
    void shouldNotDebitBookWithTypoInHolderName() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder() + "!", data.getCvv());
        purchasePage.checkIncorrectNameHolderNotification();
    }

    @Test
    @Description("Проверяется, вылезает ли при необходимости уведомление о некорректном CVV при попытке ввести двухзначный CVV")
    void shouldNotDebitBookWithTypoInCvv() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        var cvv = data.getCvv();
        var typoCvv = cvv.substring(0, 2);
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), typoCvv);
        purchasePage.checkIncorrectCvvNotification();
    }

    @Test
    @Description("Проверяется, вылезает ли при необходимости уведомление о некорректном месяце окончания срока действия карты")
    void shouldNotDebitBookWithTypoInMonth() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        var month = data.getMonth();
        var typoMonth = month.substring(0, 1);
        purchasePage.inputData(data.getCardNumber(), typoMonth, data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkIncorrectMonthNotification();
    }

    @Test
    @Description("Проверяется, вылезает ли при необходимости уведомление об некорректном месяце окончания срока действия карты, " +
            "если указан год, состоящий из одной цифры")
    void shouldNotDebitBookWithTypoInYear() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        var year = data.getYear();
        var typoYear = year.substring(0, 1);
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), typoYear, data.getCardHolder(), data.getCvv());
        purchasePage.checkIncorrectYearNotification();
    }

    @Test
    @Description("Проверяется, вылезает ли при необходимости уведомление о ошибке неверного формата данных в поле номер карты, " +
            "если ввести номер карты меньшей длины")
    void shouldNotDebitBookWithTypoInCardNumber() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        var number = data.getCardNumber();
        var typoNumber = number.substring(0, 1);
        purchasePage.inputData(typoNumber, data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkIncorrectCardNumberNotification();
    }

    @Test
    @Description("Проверяется, вылезает ли уведомление об ошибке о пустом поле Владелец")
    void shouldNotDebitBookWithEmptyHolderName() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), data.getYear(), "", data.getCvv());
        purchasePage.checkEmptyNameHolderNotification();
    }

    @Test
    @Description("Проверяется, вылезает ли уведомление об ошибкео пустом поле CVV")
    void shouldNotDebitBookWithEmptyCvv() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), "");
        purchasePage.checkIncorrectCvvNotification();
    }

    @Test
    @Description("Проверяется, вылезает ли уведомление об ошибке о неверном формате данных в поле Месяц")
    void shouldNotWriteLettersInMonth() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), "", data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkIncorrectMonthNotification();
    }

    @Test
    @Description("Проверяется, вылезает ли уведомление об ошибке о неверном формате данных в поле Год")
    void shouldNotWriteLettersInYear() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), "", data.getCardHolder(), data.getCvv());
        purchasePage.checkIncorrectYearNotification();
    }

    @Test
    @Description("Проверяется, вылезает ли уведомление об ошибке о неверном формате данных в поле Номер карты")
    void shouldNotWriteLettersInCardNumber() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData("", data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkIncorrectCardNumberNotification();
    }

    @Test
    @Description("Проверяется, вылезает ли уведомление об ошибке о неверном формате данных в поле CVV")
    void shouldNotWriteLettersInCvv() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), "");
        purchasePage.checkIncorrectCvvNotification();
    }

    @Test
    @Description("Проверяется, вылезает ли уведомление об ошибке о пустом поле Месяц")
    void shouldNotDebitBookWithEmptyMonth() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), "", data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkIncorrectMonthNotification();
    }

    @Test
    @Description("Проверяется, вылезает ли уведомление об ошибке о пустом  поле Год")
    void shouldNotDebitBookWithEmptyYear() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), "", data.getCardHolder(), data.getCvv());
        purchasePage.checkIncorrectYearNotification();
    }

    @Test
    @Description("Проверяется, вылезает ли уведомление об ошибке о пустом полет Номер карты")
    void shouldNotDebitBookWithEmptyCardNumber() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData("", data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkIncorrectCardNumberNotification();
    }

    @Test
    @Description("Проверяется, вылезает ли уведомление об ошибке о пустом поле CVV, если попытаться заполнить его буквами")
    void CvvShouldNotHaveLetters() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), "а");
        purchasePage.checkCvvFieldIsEmpty();
    }

    @Test
    @Description("Проверяется, вылезает ли уведомление об ошибке о пустом поле Месяц, если попытаться заполнить его буквами")
    void MonthShouldNotHaveLetters() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), "а", data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkMonthFieldIsEmpty();
    }

    @Test
    @Description("Проверяется, вылезает ли уведомление об ошибке о пустом поле Год, если попытаться заполнить его буквами")
    void YearShouldNotHaveLetters() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), "а", data.getCardHolder(), data.getCvv());
        purchasePage.checkYearFieldIsEmpty();
    }

    @Test
    @Description("Проверяется, вылезает ли уведомление об ошибке о пустом поле Номер карты, если попытаться заполнить его буквами")
    void CardNumberShouldNotHaveLetters() {
        var dashboardPage = new DashboardPage();
        var purchasePage = new PurchasePage();
        dashboardPage.creditPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData("а", data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkCardNumberFieldIsEmpty();
    }
}
