package app.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import app.model.Movimentacao;
import app.util.db.Conexao;
import app.util.db.Dao;

public class MovimentacaoDao implements Dao<Movimentacao> {

	@Override
	public List<Movimentacao> consultAll() {
		return null;
	}

	@Override
	public void save(Movimentacao ob) {
		Connection conexao = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet result = null;
		
		String sql1 = "INSERT INTO movimentacao(data, tipo, descricao, valor) "
					+ "VALUES(?, ?, ?, ?)";
		
		String sql2 = "INSERT INTO movimentacao_caixa(id_caixa, id_mov) "
	            	+ "VALUES(?, ?)";
		
		try {
			conexao = Conexao.getConnection("controlecaixa");
			conexao.setAutoCommit(false);
			
			pstmt1 = conexao.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			pstmt1.setString(1, ob.getData());
			pstmt1.setString(2, ob.getTipo());
			pstmt1.setString(3, ob.getDescricao());
			pstmt1.setDouble(4, ob.getValor());
			
			int rowAffected = pstmt1.executeUpdate();
			
			int KeyGenerated = 0;
			
			result = pstmt1.getGeneratedKeys();
			if(result.next()) {
				KeyGenerated = result.getInt(1);
			}
			
			if(rowAffected == 1) {
				pstmt2 = conexao.prepareStatement(sql2);
				pstmt2.setInt(1, ob.getCaixa().getId());
				pstmt2.setInt(2, KeyGenerated);
				pstmt2.executeUpdate();
				
				conexao.commit();
			} else {
				conexao.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(result != null)  
					result.close();
				if(pstmt1 != null) 
					pstmt1.close();
				if(pstmt2 != null) 
					pstmt2.close();
				if(conexao != null) 
					conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Movimentacao ob) {
		
	}

	@Override
	public void delete(Movimentacao ob) {
		
	}

}
