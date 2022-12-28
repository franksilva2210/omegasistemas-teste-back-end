package app.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import app.model.Caixa;
import app.util.db.Conexao;
import app.util.db.Dao;

public class CaixaDao implements Dao<Caixa> {

	@Override
	public List<Caixa> consultAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Caixa ob) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO "
				   + "caixa(descricao, saldoInicial) "
				   + "VALUES(?,?)";
		
		try {
			conexao = Conexao.getConnection("controlecaixa");
			
			pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, ob.getDescricao());
			pstmt.setDouble(2, ob.getValInicial());
			pstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
        	try {
        		if(pstmt != null)
        			pstmt.close();
        		if(conexao != null)
        			conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
	}

	@Override
	public void update(Caixa ob) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Caixa ob) {
		// TODO Auto-generated method stub
		
	}

}
