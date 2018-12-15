package pl.codeme.jeeb.e2.bank.services.rest;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import pl.codeme.jeeb.e2.bank.backend.BankInterface;
import pl.codeme.jeeb.e2.bank.backend.TokensManager;
import pl.codeme.jeeb.e2.bank.backend.dto.Account;
import pl.codeme.jeeb.e2.bank.services.rest.data.CardInfo;

@RequestScoped
public class ATMServicesImpl implements ATMServices {

    @HeaderParam("token")
    private String token;

    @Context
    private HttpServletRequest req;

    @EJB
    private BankInterface bank;

    @EJB
    private TokensManager tokensManager;

    private String cn;

    @PostConstruct
    private void init() {
        HttpSession session = req.getSession();
        if (session.getAttribute("cn") != null) cn = session.getAttribute("cn").toString();
        System.out.println("ejb: " + bank);
    }

    @Override
    public Response authCardNumber(String cn) {
        req.getSession().setAttribute("cn", cn);

        return Response.ok("{\"status\": \"ok\",\"cn\":\"" + cn + "\"}").build();
    }

    @Override
    public Response authPin(String pin) {
        String token = tokensManager.getToken().getTokenId();

        return Response.ok("{\"status\": \"ok\",\"cn\":\"" + cn + "\",\"pin\":\"" + pin + "\"}").header("token",
                token).build();
    }

    @Override
    public Response getCardInfo() {
        System.out.println("Token: " + token);
        if (!tokensManager.contains(token)) {
            return Response.status(Status.UNAUTHORIZED).build();
        }

        Account account = bank.getAccount(cn);
        System.out.println("cn: " + cn + " a: " + account);
        return Response.ok().entity(new CardInfo(cn, account.getCustomer(), account.getAmount())).build();
    }

    @Override
    public Response withdraw(Integer amount) {
        if (!tokensManager.contains(token)) {
            return Response.status(Status.UNAUTHORIZED).build();
        }

        return Response.ok().entity(new CardInfo(cn, "ala", 1000D)).build();
    }
}
