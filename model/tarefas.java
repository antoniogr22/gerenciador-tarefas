package model;

public class tarefas {
    private int id_tarefa;
    private String titulo;
    private String descricao;
    private String prioridade; 
    private String data_entrega;
    private String status;
    private usuarios usuario;

    public tarefas(int id_tarefa, String titulo, String descricao, String prioridade, String data_entrega, String status, usuarios usuario) {
        this.id_tarefa = id_tarefa;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.data_entrega = data_entrega;
        this.status = status;
        this.usuario = usuario;
    }

    public int getid_tarefa() {
        return id_tarefa;
    }

    public void setid_tarefa(int id_tarefa) {
        this.id_tarefa = id_tarefa;
    }

    public String gettitulo() {
        return titulo;
    }

    public void settitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getdescricao() {
        return descricao;
    }

    public void setdescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getprioridade() {
        return prioridade;
    }

    public void setprioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getdata_entrega() {
        return data_entrega;
    }

    public void setdata_entrega(String data_entrega) {
        this.data_entrega = data_entrega;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    public usuarios getusuario() {
        return usuario;
    }

    public void setusuario(usuarios usuario) {
        this.usuario = usuario;
    }
}