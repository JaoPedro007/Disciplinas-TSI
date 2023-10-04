package br.edu.utfpr.td.tsi.posto.saude.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Endereco;

@Component
public class MysqlEnderecoDAO implements EnderecoDAO {

	@Autowired
	private DataSource dataSource;

	@Override
	public void inserir(Endereco endereco, Long idPaciente) {
		String sql = "insert into endereco (logradouro, numero, cep, paciente_id, bairro_id) values (?, ?, ?, ?, ?)";
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setString(1, endereco.getLogradouro());
			preparedStatement.setString(2, endereco.getNumero());
			preparedStatement.setString(3, endereco.getCep());
			preparedStatement.setLong(4, endereco.getBairro().getId());
			preparedStatement.setLong(5, idPaciente);
			preparedStatement.executeUpdate();

			conn.close();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void atualizar(Long paciente_id, Endereco end) {
		// TODO
	}

	@Override
	public void remover(Long paciente_id) {
		// TODO

	}

	@Override
	public Endereco procurar(Long paciente_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
