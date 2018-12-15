package pl.codeme.jeeb.e2.bank.backend.dto.repo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import pl.codeme.jeeb.e2.bank.backend.dto.Account;
import pl.codeme.jeeb.e2.bank.backend.dto.Account.Card;
import pl.codeme.jeeb.e2.bank.backend.dto.Repository;

@SuppressWarnings("serial")
@Mock
public class MockRepositoryDTO implements Repository {

    private List<Account> accounts;

    @PostConstruct
    private void init() {
        accounts = new ArrayList<>();
        accounts.add(new Account("1234567890", "Jan Kowalski", 1000D));
        accounts.add(new Account("1234567891", "Piotr Walski", 2000D));
        accounts.add(new Account("1234567892", "Jan Nowak", 6000D));
        accounts.add(
                new Account(
                        "1234567893",
                        "Aleksander Wielki",
                        100_000D,
                        new Card("123456", 1000D),
                        new Card("234567", 0D)
                )
        );
    }

    public Account findAccountByCardNumber(String cardNumber) {
        for (Account item : accounts) {
            if (item.hasCard(cardNumber)) {
                return item;
            }
        }

        return null;
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