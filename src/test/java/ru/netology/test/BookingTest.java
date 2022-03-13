package ru.netology.test;

import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import static com.codeborne.selenide.Selenide.open;


public class BookingTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }


    @Test
    void shouldDebitBookWithCorrectCard(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        debitInputPage.correctDataInput(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        debitInputPage.ShowSuccessPurchaseNotification();

    }

    @Test
    void IfSuccessPurchaseFailedNotificationNoShow() {
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        debitInputPage.correctDataInput(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        debitInputPage.NoShowFailedPurchaseNotification();
    }

    @Test
    void shouldNotDebitBookWithUnCorrectCard(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getIncorrectCardInfo();
        debitInputPage.IncorrectDataInput(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
       debitInputPage.ShowFailedPurchaseNotification();
    }

    @Test
    void IfFailedPurchaseSuccessNotificationNoShow(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getIncorrectCardInfo();
        debitInputPage.IncorrectDataInput(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        debitInputPage.NoShowSuccessPurchaseNotification();
    }

    @Test
    void shouldNotDebitBookWithInvalidYear(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        debitInputPage.correctDataInput(data.getCardNumber(), data.getMonth(), "20", data.getCardHolder(), data.getCvv());
        debitInputPage.InvalidYearNotification();
    }

    @Test
    void shouldNotDebitBookWithTypoInHolderName(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        debitInputPage.correctDataInput(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder()+"!", data.getCvv());
        debitInputPage.IncorrectNameHolderNotification();
           }
    @Test
    void shouldNotDebitBookWithTypoInCvv(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        var cvv=data.getCvv();
        var typoCvv = cvv.substring(0,2);
        debitInputPage.correctDataInput(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), typoCvv);
        debitInputPage.IncorrectCvvNotification();
    }

    @Test
    void shouldNotDebitBookWithTypoInMonth(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        var month=data.getMonth();
        var typoMonth = month.substring(0,1);
        debitInputPage.correctDataInput(data.getCardNumber(),typoMonth, data.getYear(), data.getCardHolder(), data.getCvv());
        debitInputPage.IncorrectMonthNotification();
    }

    @Test
    void shouldNotDebitBookWithTypoInYear(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        var year=data.getYear();
        var typoYear = year.substring(0,1);
        debitInputPage.correctDataInput(data.getCardNumber(),data.getMonth(), typoYear, data.getCardHolder(), data.getCvv());
        debitInputPage.IncorrectYearNotification();
    }

    @Test
    void shouldNotDebitBookWithTypoInCardNumber(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        var number=data.getCardNumber();
        var typoNumber = number.substring(0,1);
        debitInputPage.correctDataInput(typoNumber,data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        debitInputPage.IncorrectCardNumberNotification();
    }
    @Test
    void shouldNotDebitBookWithEmptyHolderName(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        debitInputPage.correctDataInput(data.getCardNumber(), data.getMonth(), data.getYear(), "", data.getCvv());
        debitInputPage.EmptyNameHolderNotification();
    }

    @Test
    void shouldNotDebitBookWithEmptyCvv(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        debitInputPage.correctDataInput(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), "");
        debitInputPage.IncorrectCvvNotification();
    }

    @Test
    void shouldNotWriteLettersInMonth(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        debitInputPage.correctDataInput(data.getCardNumber(),"", data.getYear(), data.getCardHolder(), data.getCvv());
        debitInputPage.IncorrectMonthNotification();
    }

    @Test
    void shouldNotWriteLettersInYear(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        debitInputPage.correctDataInput(data.getCardNumber(),data.getMonth(), "", data.getCardHolder(), data.getCvv());
        debitInputPage.IncorrectYearNotification();
    }

    @Test
    void shouldNotWriteLettersInCardNumber(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        debitInputPage.correctDataInput("",data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        debitInputPage.IncorrectCardNumberNotification();
    }
    @Test
    void shouldNotWriteLettersInCvv(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        debitInputPage.correctDataInput(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), "");
        debitInputPage.IncorrectCvvNotification();
    }

    @Test
    void shouldNotDebitBookWithEmptyMonth(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        debitInputPage.correctDataInput(data.getCardNumber(),"", data.getYear(), data.getCardHolder(), data.getCvv());
        debitInputPage.IncorrectMonthNotification();
    }

    @Test
    void shouldNotDebitBookWithEmptyYear(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        debitInputPage.correctDataInput(data.getCardNumber(),data.getMonth(), "", data.getCardHolder(), data.getCvv());
        debitInputPage.IncorrectYearNotification();
    }

    @Test
    void shouldNotDebitBookWithEmptyCardNumber(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        debitInputPage.correctDataInput("",data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        debitInputPage.IncorrectCardNumberNotification();
    }

    @Test
    void CvvShouldNotHaveLetters(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        debitInputPage.correctDataInput(data.getCardNumber(), data.getMonth(), data.getYear(), data.getCardHolder(), "а");
        debitInputPage.NoLettersInCvv();
    }

    @Test
    void MonthShouldNotHaveLetters(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        debitInputPage.correctDataInput(data.getCardNumber(),"а", data.getYear(), data.getCardHolder(), data.getCvv());
        debitInputPage.NoLettersInMonth();
    }

    @Test
    void YearShouldNotHaveLetters(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        debitInputPage.correctDataInput(data.getCardNumber(),data.getMonth(), "а", data.getCardHolder(), data.getCvv());
        debitInputPage.NoLettersInYear();
    }

    @Test
    void CardNumberShouldNotHaveLetters(){
        var dashboardPage=new DashboardPage();
        var debitInputPage=dashboardPage.debitPurchase();
        DataHelper.CardInfo data=DataHelper.getCorrectCardInfo();
        debitInputPage.correctDataInput("а",data.getMonth(), data.getYear(), data.getCardHolder(), data.getCvv());
        debitInputPage.NoLettersInCardNumber();
    }



}
