package pl.codeme.jeeb.e2.bank.backend.model.type.converter;

import java.math.BigDecimal;

import javax.persistence.AttributeConverter;


public class AmountConverter implements AttributeConverter<BigDecimal, Double> {

    @Override
    public Double convertToDatabaseColumn(BigDecimal attribute) {
        return attribute.doubleValue();
    }

    @Override
    public BigDecimal convertToEntityAttribute(Double dbData) {
        return new BigDecimal(dbData);
    }
}
