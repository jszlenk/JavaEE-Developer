package pl.codeme.jeeb.e2.bank.backend.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import pl.codeme.jeeb.e2.bank.backend.model.type.converter.AmountConverter;

@SuppressWarnings("serial")
@Entity
@Table(
        name = "cards",
        indexes = {
                @Index(name = "IX_CREDIT_CARD_NUMBER", columnList = "card_number")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "card_number")
        }
)
public class Card extends Model {

    @Column(name = "card_number", columnDefinition = "varchar(16) NOT NULL COMMENT 'Numer karty'")
    private String cardNumber;

    @Column(columnDefinition = "decimal(10,4) NOT NULL DEFAULT 0 COMMENT 'Wartość debetu karty'")
    @Convert(converter = AmountConverter.class)
    private BigDecimal debet;

    @Column(columnDefinition = "varchar(4) COMMENT 'PIN do karty'")
    private String pin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "account_id",
            referencedColumnName = "id",
            columnDefinition = "int(11) UNSIGNED COMMENT 'Identyfikator konta bankowego'"
    )
    private Account account;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getDebet() {
        return debet;
    }

    public void setDebet(BigDecimal debet) {
        this.debet = debet;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
