package pl.codeme.jeeb.e2.bank.backend;

import javax.ejb.Local;

import pl.codeme.jeeb.e2.bank.backend.dto.Account;

@Local
public interface BankInterface {

    public Account getAccount(String cardNumber);

}
