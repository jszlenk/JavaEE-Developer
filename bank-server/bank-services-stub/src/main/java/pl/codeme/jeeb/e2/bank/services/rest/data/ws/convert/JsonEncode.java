package pl.codeme.jeeb.e2.bank.services.rest.data.ws.convert;

import java.lang.reflect.Field;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder.Text;
import javax.websocket.EndpointConfig;

public class JsonEncode implements Text<Message> {

    @Override
    public void destroy() { }

    @Override
    public void init(EndpointConfig config) { }

    @Override
    public String encode(Message message) throws EncodeException {
        JsonObjectBuilder json = Json.createObjectBuilder();
        json.add("class", message.getClass().getCanonicalName());
        JsonObjectBuilder values = Json.createObjectBuilder();
        try {
            for(Field field : message.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(message);
                if(value instanceof String) values.add(field.getName(), value.toString());
                if(value instanceof Number) values.add(field.getName(), (Double)value);
                if(value instanceof Boolean) values.add(field.getName(), (Boolean)value);
                if(value instanceof Enum) values.add(field.getName(), value.toString());
            }
        } catch(IllegalAccessException | IllegalArgumentException e) {
            json.add("error", e.getMessage());
        }
        json.add("values", values);

        return json.build().toString();
    }

}
