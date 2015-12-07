
package classes;

public class Produto {
    private String name;
    private double price;
    private String image;
    private String description;
    private int id;

    public int getId(){
        return this.id;
    }
    public void setCodigo(int id){
        this.id = id;
    }
    public String getNome() {
        return name;
    }
    public void setNome(String nome) {
        this.name = nome;
    }
    public double getPreco() {
        return price;
    }
    public void setPreco(double preco) {
        this.price = preco;
    }
    public String getFoto() {
        return image;
    }
    public void setImagem(String foto) {
        this.image = foto;
    }
    public String getDescricao() {
        return description;
    }
    public void setDescricao(String descricao) {
        this.description = descricao;
    }
}
