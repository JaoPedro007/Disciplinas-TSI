package br.edu.utfpr.td.tsi.posto.saude.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Bairro;

@Component
public class MysqlBairroDAO implements BairroDAO {
	
	@Autowired
	private DataSource dataSource;

	@Override
	public void inserir(Bairro bairro) {
		String sql = "INSERT INTO BAIRRO(nome) values (?)";
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setString(1, bairro.getNome());
			preparedStatement.executeUpdate();

			conn.close();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void atualizar(Long id, Bairro bairro) {
		String sql = "update bairro set nome = ? where id = ?";
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setString(1, bairro.getNome());
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
		String sql = "delete from bairro  where id = ?";
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
	public List<Bairro> listarTodos() {
		ArrayList<Bairro> bairros = new ArrayList<>();
		try {
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select id, nome from bairro");
			while (rs.next()) {
				Long id = rs.getLong(1);
				String nome = rs.getString(2);
				bairros.add(new Bairro(id, nome));
			}
			conn.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bairros;
	}

	@Override
	public Bairro procurar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

