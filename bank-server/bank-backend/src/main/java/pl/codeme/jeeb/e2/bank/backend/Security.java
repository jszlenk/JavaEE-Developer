package pl.codeme.jeeb.e2.bank.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import pl.codeme.jeeb.e2.bank.backend.logger.Log;

@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class Security implements TokensManager {

    @Inject
    @Log
    private Logger log;

    private ConcurrentMap<String, Token> tokens;

    public Security() {
        tokens = new ConcurrentHashMap<>();
    }

    @Override
    public Token getToken() {
        Long now = System.currentTimeMillis();
        Token token = new Token(String.valueOf(now), now + 300_000);

        tokens.put(token.getTokenId(), token);
        return token;
    }

    @Lock // default WRITE
    @Override
    public boolean contains(String token) {
        log.info("contains1: " + token);
        log.info("contains2: " + tokens.containsKey(token));
        if (tokens.containsKey(token)) {
            tokens.get(token).updateExpiranceDate();

            return true;
        }

        return false;
    }

    @Lock(LockType.READ)
    @Schedule(second = "30")
    private void updateTokens() {
        List<String> remove = new ArrayList<>();
        for (Token token : tokens.values()) {
            if (token.getExpirenceDate() <= System.currentTimeMillis()) {
                remove.add(token.getTokenId());
            }
        }

        for (String token : remove) {
            tokens.remove(token);
        }
    }

    public class Token {

        private String tokenId;
        private Long lifeTime;
        private Long expirenceDate;

        private Token(String token, Long lifeTime) {
            this.tokenId = token;
            this.lifeTime = lifeTime;
            updateExpiranceDate();
        }

        public String getTokenId() {
            return tokenId;
        }

        Long getExpirenceDate() {
            return expirenceDate;
        }

        void updateExpiranceDate() {
            expirenceDate = System.currentTimeMillis() + lifeTime;
        }
    }
}
