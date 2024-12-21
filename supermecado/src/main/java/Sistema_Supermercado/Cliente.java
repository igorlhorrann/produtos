package Sistema_Supermercado;

public class Cliente {

   //Atributos de cliente todos privados
    private String nome;
    private String email;
    private int id;
    private String telefone;

    // Método construtor da classe cliente
    public Cliente(String nome, String email, String telefone, int id) {
        this.nome = nome;
        this.email = email;
        this.id = id;
        this.telefone = telefone;
    }
    // cria contrutor vazio
    public Cliente(){}
    
    // Criei os metodos get
    public String getNome() {
        return nome;
    }
    public int getIdCliente(){
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }
   // Criei os metodos set
    public void setEmail(String email) {
        if(!email.contains("@")){
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = email;
    }

    public void setTelefone(String telefone) {
        if(!telefone.matches("\\d{10,15}")){
            throw new IllegalArgumentException("Telefone inválido");
        }
        this.telefone = telefone;
    }
}