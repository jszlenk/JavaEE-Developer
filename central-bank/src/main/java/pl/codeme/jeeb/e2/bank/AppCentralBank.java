package pl.codeme.jeeb.e2.bank;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

import pl.codeme.jeeb.e2.bank.services.rest.data.ws.CentralBankOrder;
import pl.codeme.jeeb.e2.bank.services.rest.data.ws.Order;

public class AppCentralBank {

    public static void main(String[] args) throws Exception {
        try (CentralBank socket = new CentralBank(new EventExecutor())) {
            WebSocketContainer ws = ContainerProvider.getWebSocketContainer();
            ws.connectToServer(socket, new URI("ws://localhost:8080/bank-services/central/1234567890"));
            socket.sendMessage(new CentralBankOrder("1234567890", Order.LOCK));
        }
        System.exit(0);
    }
}
