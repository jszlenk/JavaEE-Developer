package pl.codeme.jeeb.e2.bank.backend.dto.repo;

import javax.inject.Inject;

import pl.codeme.jeeb.e2.bank.backend.db.DataBase;
import pl.codeme.jeeb.e2.bank.backend.db.ManagerDB;
import pl.codeme.jeeb.e2.bank.backend.db.QuerisNames;
import pl.codeme.jeeb.e2.bank.backend.dto.Account;
import pl.codeme.jeeb.e2.bank.backend.dto.Repository;

@SuppressWarnings("serial")
@DataBase
public class RepositoryDTO implements Repository {

    @Inject
    private ManagerDB db;

    @Override
    public Account findAccountByCardNumber(String cardNumber) {
        return db.executeNamedSingle(
                QuerisNames.ACCOUNT_BY_CN,
                Account.class,
                ManagerDB.createQueryParameter("cn", cardNumber)
        );
    }

    @Override
    public Account findAccountByNumber(String accountNumber) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Account findAccountByCustomerId(Long customerId) {
        // TODO Auto-generated method stub
        return null;
    }
}
