package br.com.localcode60.modelo;

public class MarcaTipo {

    private int marcaTipo;
    private int tipoMarca;
    private Marca marca;
    private Tipo tipo;

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getMarcaTipo() {
        return marcaTipo;
    }

    public void setMarcaTipo(int marcaTipo) {
        this.marcaTipo = marcaTipo;
    }

    public int getTipoMarca() {
        return tipoMarca;
    }

    public void setTipoMarca(int tipoMarca) {
        this.tipoMarca = tipoMarca;
    }

}
