package dao;

import conexao.conexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class tarefas_tagsDAO {

    public void vincularTagATarefa(int idTarefa, int idTag) {
        String sql = "INSERT INTO tarefas_tags (tarefa_id, tag_id) VALUES (?, ?)";
        
        try (Connection conn = conexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idTarefa);
            stmt.setInt(2, idTag);
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao vincular tag à tarefa: " + e.getMessage());
        }
    }
}