package br.zup.bootcamp.nossoCartao.Cartao.Converter;

import br.zup.bootcamp.nossoCartao.Cartao.Enum.StatusCartaoEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StatusCartaoConverter implements AttributeConverter<StatusCartaoEnum, String> {
    @Override
    public String convertToDatabaseColumn(StatusCartaoEnum attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public StatusCartaoEnum convertToEntityAttribute(String dbData) {
        return StatusCartaoEnum.parse(dbData);
    }
}
