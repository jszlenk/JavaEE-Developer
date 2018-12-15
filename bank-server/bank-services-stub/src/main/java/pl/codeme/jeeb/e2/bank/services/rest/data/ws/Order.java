package pl.codeme.jeeb.e2.bank.services.rest.data.ws;


public enum Order {

    LOCK, UNLOCK;

    public static Order getValueByText(String text) {
        for(Order item : Order.values()) {
            if(item.toString().equals(text)) {
                return item;
            }
        }

        return null;
    }

}
