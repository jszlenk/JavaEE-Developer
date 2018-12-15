package pl.codeme.jeeb.e2.bank.backend;

import javax.ejb.Local;

import pl.codeme.jeeb.e2.bank.backend.Security.Token;

@Local
public interface TokensManager {

    public Token getToken();

    public boolean contains(String token);
}
