package pl.codeme.jeeb.e2.bank.backend.model.type;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValue {

    private Long timestamp;

    public DateValue(Long timestamp) {
        this.timestamp = timestamp;
    }

    public DateValue(String date, String pattern) throws ParseException {
        DateFormat df = new SimpleDateFormat(pattern);

        this.timestamp = df.parse(date).getTime();
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getFormatted(String pattern) {
        DateFormat formatter = new SimpleDateFormat(pattern);

        return formatter.format(new Date(timestamp));
    }
}
