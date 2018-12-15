package pl.codeme.jeeb.e2.bank.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(
        name = "customers"
)
public class Customer extends User {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private List<Account> accounts = new ArrayList<>();

    public void addBankAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}