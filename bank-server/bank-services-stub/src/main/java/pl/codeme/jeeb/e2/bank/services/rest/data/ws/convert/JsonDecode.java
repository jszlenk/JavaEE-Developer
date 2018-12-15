package pl.codeme.jeeb.e2.bank.services.rest.data.ws.convert;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;

import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.websocket.DecodeException;
import javax.websocket.Decoder.Text;
import javax.websocket.EndpointConfig;

import pl.codeme.jeeb.e2.bank.services.rest.data.ws.Order;

public class JsonDecode implements Text<Message> {

    @Override
    public void destroy() { }

    @Override
    public void init(EndpointConfig config) { }

    @Override
    public Message decode(String source) throws DecodeException {
        System.out.println("DECODE: " + source);
        JsonObject json = Json.createReader(new ByteArrayInputStream(source.getBytes())).readObject();
        String className = json.getString("class");
        try {
            Object obj = Class.forName(className).newInstance();
            JsonObject values = json.getJsonObject("values");
            for(Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if(values.containsKey(field.getName())) {
                    JsonValue value = values.get(field.getName());
                    switch(value.getValueType()) {
                        case FALSE:
                            field.setBoolean(obj, false);
                            break;
                        case TRUE:
                            field.setBoolean(obj, true);
                            break;
                        case NUMBER:
                            field.setDouble(obj, ((JsonNumber)value).doubleValue());
                            break;
                        case STRING:
                            Object val;
                            if(field.getType().isEnum()) {
                                val = Order.getValueByText(((JsonString)value).getString());
                            } else {
                                val = ((JsonString)value).getString();
                            }
                            field.set(obj, val);
                            break;
                        case NULL:
                        default:
                            field.set(obj, null);
                    }
                }
            }

            return (Message)obj;
        } catch(InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean willDecode(String source) {
        try {
            JsonObject json = Json.createReader(new ByteArrayInputStream(source.getBytes())).readObject();
            if(json.containsKey("class") && json.containsKey("values")) {
                return true;
            }
        } catch(Exception e) { }

        return false;
    }

}
