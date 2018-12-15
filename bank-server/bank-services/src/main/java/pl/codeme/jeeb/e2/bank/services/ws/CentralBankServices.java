package pl.codeme.jeeb.e2.bank.services.ws;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import pl.codeme.jeeb.e2.bank.services.rest.data.ws.convert.JsonDecode;
import pl.codeme.jeeb.e2.bank.services.rest.data.ws.convert.JsonEncode;
import pl.codeme.jeeb.e2.bank.services.rest.data.ws.convert.Message;

@ServerEndpoint(
    value = "/central/{account}",
    encoders = JsonEncode.class,
    decoders = {JsonDecode.class}
)
public class CentralBankServices {

    private Session session;
    private String account;

    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig, @PathParam("account") String account) {
        System.out.println("OPEN: " + account + " - " + session.getId());
        this.session = session;
        this.account = account;
    }

    @OnMessage
    public void onMessage(Message message) throws IOException {
        System.out.println("MSG: " + message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) throws Exception {
        System.out.println("ERR: " + throwable.getMessage());
        session.getBasicRemote().sendText("Error: " + throwable.getMessage());
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("CLOSE: " + closeReason.getReasonPhrase());
    }

}
