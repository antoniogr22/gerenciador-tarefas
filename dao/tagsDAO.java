package dao;

import conexao.conexaoBanco;
import model.tags;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class tagsDAO {

    public List<tags> listarTodas() {
        List<tags> lista = new ArrayList<>();

        String sql = "SELECT * FROM tags ORDER BY nome";

        try (Connection conn = conexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tags t = new tags();

                t.setid(rs.getInt("id"));
                t.setnome(rs.getString("nome"));

                lista.add(t);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar tags: " + e.getMessage());
        }

        return lista;
    }
}