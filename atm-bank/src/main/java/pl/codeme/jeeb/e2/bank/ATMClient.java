package pl.codeme.jeeb.e2.bank;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import pl.codeme.jeeb.e2.bank.services.rest.ATMServices;
import pl.codeme.jeeb.e2.bank.services.rest.data.CardInfo;

public class ATMClient {

    private Client client;
    private ResteasyWebTarget target;
    private String endpoint;

    ATMClient(String endpoint) {
        this.endpoint = endpoint;
        reset();
    }

    ATMServices getServicesProxy() {
        return target.proxy(ATMServices.class);
    }

    void setToken(String token) {
        client.register((ClientRequestFilter) ctx -> ctx.getHeaders().add("token", token));
    }

    public static void main(String[] args) {
        ATMClient atm = new ATMClient("http://localhost:8080/bank-services/rest");
        atm.getServicesProxy().authCardNumber("123456");
        atm.setToken(atm.getServicesProxy().authPin("1234").getHeaderString("token"));
        CardInfo ci = atm.getServicesProxy().getCardInfo().readEntity(CardInfo.class);
        System.out.println(ci.getCustomer());
    }

    void reset() {
        if (target != null && !target.getResteasyClient().isClosed()) {
            target.getResteasyClient().close();
        }

        client = ResteasyClientBuilder.newClient();
        target = (ResteasyWebTarget) client.target(UriBuilder.fromPath(endpoint));
    }

}
