package app.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection getConnection(String nomeBanco) {
		Connection conexao = null;
		
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nomeBanco, "root", "root");
			System.out.println("Conexão com Banco de Dados Realizada com Sucesso!");
		} catch (SQLException exc) {
			System.out.println("Erro na conexão com o banco de dados! ");
			exc.printStackTrace();
		}
		return conexao;
	}
}
