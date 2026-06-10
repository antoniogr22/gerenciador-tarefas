package model;

public class tarefas {
    private int id_tarefa;
    private String titulo;
    private String descricao;
    private String prioridade;
    private String data_entrega;
    private String status;
    private int id_categoria; // Adicionado
    private usuarios usuario;

    public tarefas(int id_tarefa, String titulo, String descricao, String prioridade, String data_entrega,
            String status, int id_categoria, usuarios usuario) {
        this.id_tarefa = id_tarefa;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.data_entrega = data_entrega;
        this.status = status;
        this.id_categoria = id_categoria; // Adicionado
        this.usuario = usuario;
    }

    // Getters e Setters
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

    public int getId_categoria() {
        return id_categoria;
    } // Adicionado

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    } // Adicionado

    public usuarios getusuario() {
        return usuario;
    }

    public void setusuario(usuarios usuario) {
        this.usuario = usuario;
    }
}