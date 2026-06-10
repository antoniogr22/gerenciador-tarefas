package model;

public class tags {
    private int id;
    private String nome;

    public tags() {
    }

    public tags(int id, String nome) {
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