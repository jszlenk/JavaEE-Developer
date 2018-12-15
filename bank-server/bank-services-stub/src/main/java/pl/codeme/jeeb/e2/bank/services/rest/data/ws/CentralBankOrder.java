package pl.codeme.jeeb.e2.bank.services.rest.data.ws;

import pl.codeme.jeeb.e2.bank.services.rest.data.ws.convert.Message;

public class CentralBankOrder implements Message {

    private String accountNumber;
    private Order order;

    public CentralBankOrder() { }

    public CentralBankOrder(String accountNumber, Order order) {
        super();
        this.accountNumber = accountNumber;
        this.order = order;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return accountNumber + " - " + order.toString();
    }
}
