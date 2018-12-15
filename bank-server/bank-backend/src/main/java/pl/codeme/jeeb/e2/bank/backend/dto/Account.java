package pl.codeme.jeeb.e2.bank.backend.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Account {

    private String accountNumber;
    private String customer;
    private Double amount;
    private List<Card> cards;

    public Account(String accountNumber, String customer, Double amount) {
        this(accountNumber, customer, amount, new Card[]{});
    }

    public Account(String accountNumber, String customer, Double amount, Card... cards) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.amount = amount;
        this.cards = new ArrayList<>();
        this.cards.addAll(Arrays.asList(cards));
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomer() {
        return customer;
    }

    public Double getAmount() {
        return amount;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    @SuppressWarnings("unlikely-arg-type")
    public boolean hasCard(String cardNumber) {
        for (Card item : cards) {
            if (item.equals(cardNumber)) return true;
        }

        return false;
    }

    public static class Card {

        private String cardNumber;
        private BigDecimal debet;

        public Card(String cardNumber, BigDecimal debet) {
            this.cardNumber = cardNumber;
            this.debet = debet;
        }

        public Card(String cardNumber, Double debet) {
            this.cardNumber = cardNumber;
            this.debet = new BigDecimal(debet);
        }

        String getCardNumber() {
            return cardNumber;
        }

        public BigDecimal getDebet() {
            return debet;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Card) {
                return ((Card) obj).getCardNumber().equals(this.cardNumber);
            }

            if (obj instanceof String) {
                return (obj.toString().equals(this.getCardNumber()));
            }

            return false;
        }
    }
}
