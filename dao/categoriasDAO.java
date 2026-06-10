package dao;

import conexao.conexaoBanco;
import model.categorias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class categoriasDAO {

    public List<categorias> listarTodas() {
        List<categorias> lista = new ArrayList<>();

        String sql = "SELECT * FROM categorias ORDER BY nome";

        try (Connection conn = conexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                categorias c = new categorias();
                c.setid(rs.getInt("id"));
                c.setnome(rs.getString("nome"));
                lista.add(c);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar categorias: " + e.getMessage());
        }

        return lista;
    }
}