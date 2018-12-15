package pl.codeme.jeeb.e2.bank.services.rest.data;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "amount", "cardNumber", "customer" })
@XmlAccessorType(FIELD)
public class CardInfo {

    @XmlElement(name = "cn", required = true)
    private String cardNumber;

    @XmlElement(defaultValue = "0")
    private Double amount;

    @XmlElement(name = "name", required = true)
    private String customer;

    @XmlTransient
    private String accountNumber;

    public CardInfo() {
    }

    public CardInfo(String cardNumber, String customer, Double amount) {
        this.cardNumber = cardNumber;
        this.customer = customer;
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCustomer() {
        return customer;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

}
