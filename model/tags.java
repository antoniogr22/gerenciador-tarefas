package model;

public class tags {
    private int id;
    private int usuario_id; 
    private String nome;

    public tags(int id, int usuario_id, String nome) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.nome = nome;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public int getusuario_id() {
        return usuario_id;
    }

    public void setusuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getnome() {
        return nome;
    }

    public void setnome(String nome) {
        this.nome = nome;
    }
}