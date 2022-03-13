package ru.netology.data;

import lombok.Value;
import lombok.var;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvv;
    }

    public static CardInfo getCorrectCardInfo() {
        return new CardInfo("4444 4444 4444 4441", "10", "23", "Ivan Ivanov", "555");
    }

    public static CardInfo getIncorrectCardInfo() {
        return new CardInfo("4444 4444 4444 4442", "10", "23", "Ivan Ivanov", "555");
    }
}