package br.edu.fesa.aquela_loja.models.enums;

public enum OrderStatusEnum {
    WAITING_PAYMENT("PROCESSANDO PAGAMENTO"),
    PREPARING("PREPARANDO PEDIDO"),
    DISPATCHED("ENTREGUE NA TRANSPORTADORA"),
    DELIVERED("ENTREGUE");


    private final String displayName;

    OrderStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
