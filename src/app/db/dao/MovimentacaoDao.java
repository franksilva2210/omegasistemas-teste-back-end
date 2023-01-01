package app.db.dao;

import java.util.List;

import app.model.Movimentacao;
import app.util.db.Dao;

public class MovimentacaoDao implements Dao<Movimentacao> {

	@Override
	public List<Movimentacao> consultAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Movimentacao ob) {
		/*
		Connection conexao = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet result = null;
		
		String sql1 = "INSERT INTO movimentacao(data, tipo, descricao, valor) "
					+ "VALUES(?, ?, ?, ?)";
		
		String sql2 = "INSERT INTO movimentacao_caixa(id_caixa, id_mov) "
	            	+ "VALUES(?, ?)";
		
		try {
			conexao = Conexao.getConnection("app2");
			conexao.setAutoCommit(false);
			
			pstmt1 = conexao.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			pstmt1.setString(1, ob.getData());
			pstmt1.setString(2, ob.getTipo());
			pstmt1.setString(3, ob.getDescricao());
			pstmt1.setDouble(4, ob.getValor());
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
		
		}
		*/
		
	}

	@Override
	public void update(Movimentacao ob) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Movimentacao ob) {
		// TODO Auto-generated method stub
		
	}

}
