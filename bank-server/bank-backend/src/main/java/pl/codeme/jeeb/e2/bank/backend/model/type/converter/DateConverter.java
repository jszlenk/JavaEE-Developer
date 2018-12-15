package pl.codeme.jeeb.e2.bank.backend.model.type.converter;

import javax.persistence.AttributeConverter;

import pl.codeme.jeeb.e2.bank.backend.model.type.DateValue;

public class DateConverter implements AttributeConverter<DateValue, Long> {

    @Override
    public Long convertToDatabaseColumn(DateValue attribute) {
        if (attribute != null) {
            return attribute.getTimestamp();
        }

        return null;
    }

    @Override
    public DateValue convertToEntityAttribute(Long dbData) {
        if (dbData != null) {
            return new DateValue(dbData);
        }

        return null;
    }
}
