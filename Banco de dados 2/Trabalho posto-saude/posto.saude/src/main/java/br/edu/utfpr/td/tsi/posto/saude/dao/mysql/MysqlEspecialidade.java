package br.edu.utfpr.td.tsi.posto.saude.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.posto.saude.dao.EspecialidadeDAO;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Especialidade;


public class MysqlEspecialidade implements EspecialidadeDAO{
	@Autowired
	private DataSource dataSource;

	@Override
	public void inserir(Especialidade especialidade) {
		String sql = "INSERT INTO especialidade(descricao) values (?)";
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setString(1, especialidade.getDescricao());
			preparedStatement.executeUpdate();

			conn.close();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void atualizar(Long id, Especialidade especialidade) {
		String sql = "update especialidade set descricao = ? where id = ?";
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setString(1, especialidade.getDescricao());
			preparedStatement.setLong(2, id);
			preparedStatement.executeUpdate();

			conn.close();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remover(Long id) {
		String sql = "delete from especialidade  where id = ?";
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();

			conn.close();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Especialidade> listarTodas() {
		ArrayList<Especialidade> especialidades = new ArrayList<>();
		try {
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select id, descricao from especialidade");
			while (rs.next()) {
				Long id = rs.getLong(1);
				String descricao = rs.getString(2);
				especialidades.add(new Especialidade(id, descricao));
			}
			conn.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return especialidades;
	}

	@Override
	public Especialidade procurar(Long id) {
	    try (Connection conn = dataSource.getConnection();
		         PreparedStatement stmt = conn.prepareStatement("SELECT * FROM especialidade WHERE id = ?")) {
		        stmt.setLong(1, id);
		        ResultSet rs = stmt.executeQuery();

		        if (rs.next()) {
		            Long idEspecialidade = rs.getLong("id");
		            String descricao = rs.getString("descricao");
		            
		            
		            return new Especialidade(idEspecialidade, descricao);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return null;
	}
}
