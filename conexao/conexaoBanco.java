package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexaoBanco {
   
   private static final String URL = "jdbc:mysql://localhost:3306/gestao_tarefas?useSSL=false";
   private static final String USUARIO = "root";
   private static final String SENHA = "@inter2006";

   public conexaoBanco() {
   }

   public static Connection obterConexao() {
      try {
         
         return DriverManager.getConnection(URL, USUARIO, SENHA);
      } catch (SQLException e) {
         throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage());
      }
   }
}