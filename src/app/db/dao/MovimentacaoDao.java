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
import app.model.Movimentacao;
import app.util.db.Conexao;
import app.util.db.Dao;

public class MovimentacaoDao implements Dao<Movimentacao> {

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
	public List<Movimentacao> consultAll() {
		Connection conexao = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		ResultSet result2 = null;
		
		List<Movimentacao> listMovimentacao = new ArrayList<Movimentacao>();
		
		String sql = "SELECT * FROM movimentacao";
		
		String sql2 = "SELECT * FROM movimentacao_caixa " + 
					  "WHERE id_mov = ?";
		
		try {
			conexao = Conexao.getConnection("controlecaixa");
			
			stmt = conexao.createStatement();
			
			result = stmt.executeQuery(sql);
			
			while(result.next()) {
				Movimentacao movimento = new Movimentacao();
				movimento.setId(result.getInt("idmovimentacao"));
				movimento.setData(result.getString("data"));
				movimento.setDescricao(result.getString("descricao"));
				movimento.setTipo(result.getString("tipo"));
				movimento.setValor(result.getDouble("valor"));
				
				pstmt = conexao.prepareStatement(sql2);
				pstmt.setInt(1, movimento.getId());
				
				result2 = pstmt.executeQuery();
				while(result2.next()) {
					CaixaDao caixaDao = new CaixaDao();
					Caixa caixa = caixaDao.consult(result2.getInt("id_caixa"));
					movimento.setCaixa(caixa);
				}
				
				listMovimentacao.add(movimento);
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
				if(stmt != null) 
					stmt.close();
				if(conexao != null) 
					conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listMovimentacao;
	}
	
	public List<Movimentacao> consultAll(int idCaixa) {
		Connection conexao = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet result1 = null;
		ResultSet result2 = null;
		
		List<Movimentacao> listMovimentacao = new ArrayList<Movimentacao>();
		
		String sql1 = "SELECT * " + 
					  "FROM movimentacao_caixa " + 
					  "WHERE id_caixa = ?";
		
		String sql2 = "SELECT * FROM movimentacao " + 
					  "WHERE idmovimentacao = ?";
		
		try {
			conexao = Conexao.getConnection("controlecaixa");
			
			pstmt1 = conexao.prepareStatement(sql1);
			pstmt1.setInt(1, idCaixa);
			
			result1 = pstmt1.executeQuery();
			while(result1.next()) {
				pstmt2 = conexao.prepareStatement(sql2);
				pstmt2.setInt(1, result1.getInt("id_mov"));
				
				result2 = pstmt2.executeQuery();
				while(result2.next()) {
					Movimentacao movimentacao = new Movimentacao();
					movimentacao.setId(result2.getInt("idmovimentacao"));
					movimentacao.setData(result2.getString("data"));
					movimentacao.setDescricao(result2.getString("descricao"));
					movimentacao.setTipo(result2.getString("tipo"));
					movimentacao.setValor(result2.getDouble("valor"));
					
					listMovimentacao.add(movimentacao);
				}
			}
			
			error = false;
			msgError = "";
			
		} catch (SQLException e) {
			
			error = true;
			msgError = "Erro na consulta ao banco de dados";
			e.printStackTrace();
		
		} finally {
			try {
				if(result1 != null)  
					result1.close();
				if(result2 != null)  
					result2.close();
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
		
		return listMovimentacao;
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
			
			error = false;
			msgError = "";
			
		} catch (SQLException e) {
			
			error = true;
			msgError = "Erro na atualizacao ao banco de dados";
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
		Connection conexao = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		
		String sql = "UPDATE movimentacao " + 
					 "SET " + 
					 "data = ?, " + 
					 "tipo = ?, " + 
					 "descricao = ?, " + 
					 "valor = ? " + 
					 "WHERE idmovimentacao = ?;";
		
		String sql2 = "UPDATE movimentacao_caixa " +
					  "SET " +
					  "id_caixa = ? "+
					  "WHERE id_mov = ?;";
		
		try {
			conexao = Conexao.getConnection("controlecaixa");
			
			pstmt1 = conexao.prepareStatement(sql);
			pstmt1.setString(1, ob.getData());
			pstmt1.setString(2, ob.getTipo());
			pstmt1.setString(3, ob.getDescricao());
			pstmt1.setDouble(4, ob.getValor());
			pstmt1.setInt(5, ob.getId());
			pstmt1.executeUpdate();
			
			pstmt2 = conexao.prepareStatement(sql2);
			pstmt2.setInt(1, ob.getCaixa().getId());
			pstmt2.setInt(2, ob.getId());
			pstmt2.executeUpdate();
			
			error = false;
			msgError = "";
			
		} catch (SQLException e) {
			
			error = true;
			msgError = "Erro na atualizao ao banco de dados";
			e.printStackTrace();
			
		} finally {
			try {
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
	public void delete(Movimentacao ob) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM movimentacao " + 
					 "WHERE idmovimentacao = ?";
		
		try {
			conexao = Conexao.getConnection("controlecaixa");
			
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, ob.getId());
			pstmt.executeUpdate();
			
			error = false;
			msgError = "";
			
		} catch (SQLIntegrityConstraintViolationException e) {
			
			error = true;
			msgError = "Este registro possui vinculo";
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			error = true;
			msgError = "Erro na atualizao ao banco de dados";
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
}