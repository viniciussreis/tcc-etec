package br.com.localcode60.modelo;

public class Produto {

    private int id;
    private String nome;
    private int vlTamanhoProduto;
    private String nomeCor;
    private int produtoMarca;
    private int quantidade;
    private String tipo;
    private Marca marca;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVlTamanhoProduto() {
        return vlTamanhoProduto;
    }

    public void setVlTamanhoProduto(int vlTamanhoProduto) {
        this.vlTamanhoProduto = vlTamanhoProduto;
    }

    public String getNomeCor() {
        return nomeCor;
    }

    public void setNomeCor(String nomeCor) {
        this.nomeCor = nomeCor;
    }

    public int getProdutoMarca() { return produtoMarca; }

    public void setProdutoMarca(int produtoMarca) {
        this.produtoMarca = produtoMarca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
