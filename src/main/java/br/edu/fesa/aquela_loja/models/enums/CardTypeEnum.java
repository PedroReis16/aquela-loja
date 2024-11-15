package br.edu.fesa.aquela_loja.models.enums;

public enum CardTypeEnum {
    CREDIT("Crédito"),
    DEBIT("Débito");

    private final String displayName;

    private CardTypeEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
