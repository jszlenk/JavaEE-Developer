package pl.codeme.jeeb.e2.bank.backend.dto;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

@SessionScoped
@Dependent
public interface Repository extends Serializable {

    public Account findAccountByCardNumber(String cardNumber);

    public Account findAccountByNumber(String accountNumber);

    public Account findAccountByCustomerId(Long customerId);

}
