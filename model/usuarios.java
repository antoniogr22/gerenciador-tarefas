package model;

public class usuarios {
    private int id_usuario;
    private String usuario;
    private String senha;

    public usuarios(int id_usuario, String usuario, String senha) {
        this.id_usuario = id_usuario;
        this.usuario = usuario;
        this.senha = senha;
    }

    public int getid_usuario() {
        return id_usuario;
    }

    public void setid_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getusuario() {
        return usuario;
    }

    public void setusuario(String usuario) {
        this.usuario = usuario;
    }

    public String getidsenha() {
        return senha;
    }

    public void setsenha(String senha) {
        this.senha = senha;
    }

}
