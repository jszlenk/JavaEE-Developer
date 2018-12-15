package pl.codeme.jeeb.e2.bank.backend.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import pl.codeme.jeeb.e2.bank.backend.model.type.converter.AmountConverter;

@SuppressWarnings("serial")
@Entity
@Table(
        name = "accounts",
        indexes = {@Index(name = "IX_ACCOUNT_NUMBER", columnList = "account_number")},
        uniqueConstraints = {@UniqueConstraint(name = "UQ_ACCOUNT_NUMBER", columnNames = "account_number")}
)
public class Account extends Model {

    @Column(name = "account_number", columnDefinition = "varchar(26) comment 'Numer konta'")
    private String number;

    @Column(columnDefinition = "decimal(15,3) default 0 comment 'Kwota na koncie'")
    @Convert(converter = AmountConverter.class)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "id",
            columnDefinition = "int(11) unsigned COMMENT 'Identyfikator klienta'"
    )
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<Card> cards = new ArrayList<>();

    public Account() {
        super();
    }

    public Account(String number) {
        this();
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Card> getCreditCards() {
        return cards;
    }

    public void setCreditCards(List<Card> creditCards) {
        this.cards = creditCards;
    }

    public void addCreditCard(Card creditCard) {
        cards.add(creditCard);
    }
}
