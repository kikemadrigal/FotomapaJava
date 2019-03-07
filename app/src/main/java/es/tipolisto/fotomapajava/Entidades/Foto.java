package es.tipolisto.fotomapajava.Entidades;

public class Foto {
    private int id;
    private String texto;
    public Foto(){}

    public Foto(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}