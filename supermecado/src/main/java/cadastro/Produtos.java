package cadastro;

public class Produtos {
    //atributos da classe
    private String nome;
    private double preco;
    private int qtdEstoque;
    
    //metodo construtor
    public Produtos(String nome, double preco, int qtdEstoque){
    this.nome =  nome;
    this.preco = preco;
    this.qtdEstoque = qtdEstoque;
    }
    
    //metodos get e set
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeEmEstoque() {
        return qtdEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
    
    
}
