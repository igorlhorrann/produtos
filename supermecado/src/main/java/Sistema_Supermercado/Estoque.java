package Sistema_Supermercado;

public class Estoque {
    private int idEstoque;
    private int idProduto;
    private int quantidade;
    private String tipoQuantidade;

    // Getters e setters
    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoQuantidade() {
        return tipoQuantidade;
    }

    public void setTipoQuantidade(String tipoQuantidade) {
        this.tipoQuantidade = tipoQuantidade;
    }
}