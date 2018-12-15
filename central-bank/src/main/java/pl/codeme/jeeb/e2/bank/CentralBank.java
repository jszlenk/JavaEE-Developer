package pl.codeme.jeeb.e2.bank;

import java.io.Closeable;
import java.io.IOException;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import pl.codeme.jeeb.e2.bank.services.rest.data.ws.convert.JsonDecode;
import pl.codeme.jeeb.e2.bank.services.rest.data.ws.convert.JsonEncode;
import pl.codeme.jeeb.e2.bank.services.rest.data.ws.convert.Message;

@ClientEndpoint(
        encoders = JsonEncode.class,
        decoders = JsonDecode.class
)

public class CentralBank implements Closeable {

    private Session session;
    private EventExecutor executor;

    CentralBank(EventExecutor executor) {
        this.executor = executor;
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnMessage
    public void onMessage(Message message) {
        System.out.println("MSG: " + message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println(throwable.getMessage());
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("Client Close: " + closeReason.getReasonPhrase());
    }

    void sendMessage(Message message) throws IOException, EncodeException {
        session.getBasicRemote().sendObject(message);
    }

    private void disconect(String reason) throws IOException {
        if (session.isOpen()) {
            session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE, reason));
        }
    }

    @Override
    public void close() throws IOException {
        disconect("Client disconect");
    }

}
