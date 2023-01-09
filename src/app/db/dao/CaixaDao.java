package app.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.model.Caixa;
import app.util.db.Conexao;
import app.util.db.Dao;

public class CaixaDao implements Dao<Caixa> {

	private String msgError;
	private Boolean error;
	
	public String getMsgError() {
		return msgError;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	@Override
	public List<Caixa> consultAll() {
		Connection conexao = null;
		Statement stmt = null;
		ResultSet result = null;
		
		List<Caixa> listCaixa = new ArrayList<Caixa>();
		
		String sql =  "SELECT * FROM caixa ORDER BY descricao";
		
		try {
			conexao = Conexao.getConnection("controlecaixa");
			
			stmt = conexao.createStatement();
			
			result = stmt.executeQuery(sql);
			while(result.next()) {
				Caixa caixa = new Caixa();
				
				caixa.setId(result.getInt("idcaixa"));
				caixa.setDescricao(result.getString("descricao"));
				caixa.setSaldo(result.getDouble("saldoInicial"));
				
				listCaixa.add(caixa);
			}
			
			error = false;
			msgError = "";
			
		} catch (SQLException e) {
			
			error = true;
			msgError = "Erro na consulta ao banco de dados!";
			e.printStackTrace();
			
		} finally {
			try {
				if(conexao != null)
					conexao.close();
				if(stmt != null)
					stmt.close();
				if(result != null)
					result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listCaixa;
	}
	
	public Caixa consult(int id) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		Caixa caixa = null;
		
		String sql = "SELECT * FROM caixa WHERE idcaixa = ?";
		
		try {
			conexao = Conexao.getConnection("controlecaixa");
			
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			result = pstmt.executeQuery();
			while(result.next()) {
				caixa = new Caixa();
				caixa.setId(result.getInt("idcaixa"));
				caixa.setDescricao(result.getString("descricao"));
				caixa.setSaldo(result.getDouble("saldoInicial"));
			}
			
			error = false;
			msgError = "";
			
		} catch (SQLException e) {
			
			error = true;
			msgError = "Erro na consulta ao banco de dados";
			e.printStackTrace();
			
		} finally {
        	try {
        		if(result != null)
        			result.close();
        		if(pstmt != null)
        			pstmt.close();
        		if(conexao != null)
        			conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		
		return caixa;
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
			pstmt.setDouble(2, ob.getSaldo());
			pstmt.execute();
			
			error = false;
			msgError = "";
			
		} catch (SQLException e) {
		
			error = true;
			msgError = "Erro na insercao de registro ao banco de dados";
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
		Connection conexao = null;
		PreparedStatement pstmt = null;

		String sql = "UPDATE caixa "
				   + "SET descricao = ?, saldoInicial = ? "
				   + "WHERE idcaixa = ?";
		
		try {
			conexao = Conexao.getConnection("controlecaixa");
			
			pstmt = conexao.prepareStatement(sql);
			
			pstmt.setString(1, ob.getDescricao());
			pstmt.setDouble(2, ob.getSaldo());
			pstmt.setInt(3, ob.getId());
			
			pstmt.executeUpdate();
			
			error = false;
			msgError = "";
			
		} catch (SQLException e) {
			error = true;
			msgError = "Erro na atualizacao de registro ao banco de dados!";
			e.printStackTrace();
		} finally {
        	try {
        		if(conexao != null)
        			conexao.close();
        		if(pstmt != null)
        			pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Caixa ob) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM caixa "
				   + "WHERE idcaixa = ?";
		
		try {
			conexao = Conexao.getConnection("controlecaixa");
			
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, ob.getId());
			pstmt.executeUpdate();
			
			error = false;
			msgError = "";
			
		} catch (SQLIntegrityConstraintViolationException e) {
			
			error = true;
			msgError = "Este registro ja possui vinculo com outros";
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			error = true;
			msgError = "Erro na atualizacao de registro ao banco de dados";
			e.printStackTrace();
		
		} finally {
			try {
        		if(conexao != null)
        			conexao.close();
        		if(pstmt != null)
        			pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}