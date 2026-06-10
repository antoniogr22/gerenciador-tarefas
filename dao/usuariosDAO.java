package dao;

import conexao.conexaoBanco;
import model.usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class usuariosDAO {

    public usuarios fazerLogin(String usuarioTxt, String senhaTxt) {
        String sql = "SELECT `id_usuario`, `usuario`, `senha` FROM `usuarios` WHERE `usuario` = ? AND `senha` = ?";
        
        try (Connection conn = conexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuarioTxt);
            stmt.setString(2, senhaTxt);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new usuarios(
                        rs.getInt("id_usuario"),
                        rs.getString("usuario"),
                        rs.getString("senha")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fazer login: " + e.getMessage());
        }
        return null; 
    }

    public void cadastrarUsuario(usuarios usuario) {
        String sql = "INSERT INTO `usuarios` (`usuario`, `senha`) VALUES (?, ?)";
        
        try (Connection conn = conexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getusuario());
            stmt.setString(2, usuario.getsenha()); 
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
}