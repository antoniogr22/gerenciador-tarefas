
package dao;

import conexao.conexaoBanco;
import model.tarefas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class tarefasDAO {

    public List<tarefas> listarTarefas(int usuarioId) {
        List<tarefas> lista = new ArrayList<>();
        
        // Aqui está o segredo: usamos o nome da coluna que existe no banco (id_categoria)
        // e fazemos o JOIN com a tabela 'categorias' usando a coluna 'id' dela.
        String sql = "SELECT t.*, c.nome AS nome_categoria " +
                     "FROM tarefas t " +
                     "LEFT JOIN categorias c ON t.id_categoria = c.id " +
                     "WHERE t.usuario_id = ?";
        
        try (Connection conn = conexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, usuarioId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Aqui você constrói o objeto. 
                    // Certifique-se de que o construtor da sua classe 'tarefas' 
                    // suporte o novo campo 'id_categoria' que adicionamos.
                    tarefas t = new tarefas(
                        rs.getInt("id_tarefa"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getString("prioridade"),
                        rs.getString("data_entrega"),
                        rs.getString("status"),
                        rs.getInt("id_categoria"), // O campo que agora existe no banco
                        null // Objeto usuário, ajuste se necessário
                    );
                    lista.add(t);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao carregar tarefas: " + e.getMessage());
        }
        return lista;
    }
}