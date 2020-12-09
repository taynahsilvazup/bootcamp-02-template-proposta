package br.zup.bootcamp.nossoCartao.Cartao.Enum;

import br.zup.bootcamp.nossoCartao.Proposta.Enum.StatusPropostaEnum;

public enum  StatusCartaoEnum {

    ATIVO("ATIVO"),
    BLOQUEADO("BLOQUEADO");

    private final String value;

    public String getValue() {
        return value;
    }

    StatusCartaoEnum(String value) {
        this.value = value;
    }

    public static StatusCartaoEnum parse(String value) {
        if (value == null) return null;

        for (StatusCartaoEnum e : StatusCartaoEnum.values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        throw new AssertionError(value);
    }
}
