package ru.netology.test;

import lombok.SneakyThrows;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DBHelper;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;

import java.sql.Connection;
import java.sql.DriverManager;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DebitBookingTest {
    String status;



    @BeforeEach
    @SneakyThrows
    void setUp() {
        open("http://localhost:8080");
    }

    @Test
    void shouldDebitBookWithCorrectCard() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkSuccessPurchaseNotification();
        status=DBHelper.checkStatus();
        assertEquals("APPROVED",status);
    }

    @Test
    void IfSuccessPurchaseFailedNotificationNoShow() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkNoFailedPurchaseNotification();
    }

    @Test
    void shouldNotDebitBookWithUnCorrectCard() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getIncorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkFailedPurchaseNotification();
    }

    @Test
    void IfFailedPurchaseSuccessNotificationNoShow() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getIncorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkNoSuccessPurchaseNotification();
    }

    @Test
    void shouldNotDebitBookWithInvalidYear() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), "20", data.getCardHolder(), data.getCvv());
        purchasePage.checkInvalidYearNotification();
    }

    @Test
    void shouldNotDebitBookWithTypoInHolderName() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder() + "!", data.getCvv());
        purchasePage.checkIncorrectNameHolderNotification();
    }

    @Test
    void shouldNotDebitBookWithTypoInCvv() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        var cvv = data.getCvv();
        var typoCvv = cvv.substring(0, 2);
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), typoCvv);
        purchasePage.checkIncorrectCvvNotification();
    }

    @Test
    void shouldNotDebitBookWithTypoInMonth() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        var month = data.getMonth();
        var typoMonth = month.substring(0, 1);
        purchasePage.inputData(data.getCardNumber(), typoMonth, data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkIncorrectMonthNotification();
    }

    @Test
    void shouldNotDebitBookWithTypoInYear() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        var year = data.getYear();
        var typoYear = year.substring(0, 1);
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), typoYear, data.getCardHolder(), data.getCvv());
        purchasePage.checkIncorrectYearNotification();
    }

    @Test
    void shouldNotDebitBookWithTypoInCardNumber() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        var number = data.getCardNumber();
        var typoNumber = number.substring(0, 1);
        purchasePage.inputData(typoNumber, data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkIncorrectCardNumberNotification();
    }

    @Test
    void shouldNotDebitBookWithEmptyHolderName() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), data.getYear(), "", data.getCvv());
        purchasePage.checkEmptyNameHolderNotification();
    }

    @Test
    void shouldNotDebitBookWithEmptyCvv() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), "");
        purchasePage.checkIncorrectCvvNotification();
    }

    @Test
    void shouldNotWriteLettersInMonth() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), "", data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkIncorrectMonthNotification();
    }

    @Test
    void shouldNotWriteLettersInYear() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), "", data.getCardHolder(), data.getCvv());
        purchasePage.checkIncorrectYearNotification();
    }

    @Test
    void shouldNotWriteLettersInCardNumber() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData("", data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkIncorrectCardNumberNotification();
    }

    @Test
    void shouldNotWriteLettersInCvv() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), "");
        purchasePage.checkIncorrectCvvNotification();
    }

    @Test
    void shouldNotDebitBookWithEmptyMonth() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), "", data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkIncorrectMonthNotification();
    }

    @Test
    void shouldNotDebitBookWithEmptyYear() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), "", data.getCardHolder(), data.getCvv());
        purchasePage.checkIncorrectYearNotification();
    }

    @Test
    void shouldNotDebitBookWithEmptyCardNumber() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData("", data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkIncorrectCardNumberNotification();
    }

    @Test
    void CvvShouldNotHaveLetters() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), "а");
        purchasePage.checkCvvFieldIsEmpty();
    }

    @Test
    void MonthShouldNotHaveLetters() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), "а", data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkMonthFieldIsEmpty();
    }

    @Test
    void YearShouldNotHaveLetters() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData(data.getCardNumber(), data.getMonth(), "а", data.getCardHolder(), data.getCvv());
        purchasePage.checkYearFieldIsEmpty();
    }

    @Test
    void CardNumberShouldNotHaveLetters() {
        var dashboardPage = new DashboardPage();
        var purchasePage = dashboardPage.debitPurchase();
        DataHelper.CardInfo data = DataHelper.getCorrectCardInfo();
        purchasePage.inputData("а", data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        purchasePage.checkCardNumberFieldIsEmpty();
    }
}
