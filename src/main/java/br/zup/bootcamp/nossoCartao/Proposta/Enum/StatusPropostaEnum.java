package br.zup.bootcamp.nossoCartao.Proposta.Enum;

public enum StatusPropostaEnum {
    ELEGIVEL("ELEGIVEL"),
    NAO_ELEGIVEL("NAO_ELEGIVEL"),
    ;

    private final String value;

    public String getValue() {
        return value;
    }

    StatusPropostaEnum(String value) {
        this.value = value;
    }

    public static StatusPropostaEnum parse(String value) {
        if (value == null) return null;

        for (StatusPropostaEnum e : StatusPropostaEnum.values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        throw new AssertionError(value);
    }
}
