package pl.codeme.jeeb.e2.bank.backend;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import pl.codeme.jeeb.e2.bank.backend.db.DataBase;
import pl.codeme.jeeb.e2.bank.backend.dto.Account;
import pl.codeme.jeeb.e2.bank.backend.dto.Repository;
import pl.codeme.jeeb.e2.bank.backend.logger.Log;

@Stateless
public class BankManager implements BankInterface {

    @Inject
    @Log
    private Logger logger;

    @Inject
    @DataBase
    private Repository repo;

    public Account getAccount(String cardNumber) {
        logger.info("getAccount CN: " + cardNumber);
        return repo.findAccountByCardNumber(cardNumber);
    }
}
