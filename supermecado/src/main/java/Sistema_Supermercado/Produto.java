package Sistema_Supermercado;

public class Produto {
    //atributos da classe
    private int id_produto;
    private String nome_produto;
    private String descricao_produto;

    public Produto() {

    }

    //metodo construtor
    public Produto(int id_produto, String nome_produto, String descricao_produto){
        this.id_produto = id_produto;
        this.nome_produto = nome_produto;
        this.descricao_produto = descricao_produto;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public String getDescricao_produto() {
        return descricao_produto;
    }

    public void setDescricao_produto(String descricao_produto) {
        this.descricao_produto = descricao_produto;
    }
}
