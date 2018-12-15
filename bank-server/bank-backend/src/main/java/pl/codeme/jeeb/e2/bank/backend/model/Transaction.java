package pl.codeme.jeeb.e2.bank.backend.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import pl.codeme.jeeb.e2.bank.backend.model.type.DateValue;
import pl.codeme.jeeb.e2.bank.backend.model.type.TransactionType;
import pl.codeme.jeeb.e2.bank.backend.model.type.UserType;
import pl.codeme.jeeb.e2.bank.backend.model.type.converter.DateConverter;

@SuppressWarnings("serial")
@Entity
@Table(
        name = "transactions",
        indexes = {
                @Index(name = "IX_CUSTOMER", columnList = "customer_id"),
                @Index(name = "IX_EMPLOYEE", columnList = "employee_id"),
                @Index(name = "IX_BANK_ACCOUNT", columnList = "account_id")
        }
)

public class Transaction extends Model {

    @Column(name = "transaction_date", columnDefinition = "decimal(14,0) UNSIGNED NOT NULL COMMENT 'Data tranzakcji'")
    @Convert(converter = DateConverter.class)
    private DateValue transactionDate;

    @Column(name = "transaction_value", columnDefinition = "decimal(10,4) NOT NULL COMMENT 'Kwota transakcji'")
    private Double transactionValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", columnDefinition = "enum('PAY', 'PAYOFF') NOT NULL COMMENT 'Typ transakcji'")
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_owner", columnDefinition = "enum('CUSTOMER', 'EMPLOYEE') NOT NULL COMMENT 'Właściciel" +
            " transakcji'")
    private UserType transactionOwner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "id",
            columnDefinition = "int(11) unsigned COMMENT 'Identyfikator klienta'"
    )
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "employee_id",
            referencedColumnName = "id",
            columnDefinition = "int(11) unsigned COMMENT 'Identyfikator pracownika'"
    )
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "account_id",
            referencedColumnName = "id",
            columnDefinition = "int(11) unsigned COMMENT 'Identyfikator konta bankowego'"
    )
    private Account account;

    @PrePersist
    @PreUpdate
    public void defaultValues() {
        if (transactionDate == null) transactionDate = new DateValue(System.currentTimeMillis());
    }

    public DateValue getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(DateValue transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(Double transactionValue) {
        this.transactionValue = transactionValue;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public UserType getTransactionOwner() {
        return transactionOwner;
    }

    public void setTransactionOwner(UserType transactionOwner) {
        this.transactionOwner = transactionOwner;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
