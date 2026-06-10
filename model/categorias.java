package model;

public class categorias {
    private int id;
    private String nome;

    public categorias() {
    }

    public categorias(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getnome() {
        return nome;
    }

    public void setnome(String nome) {
        this.nome = nome;
    }
}