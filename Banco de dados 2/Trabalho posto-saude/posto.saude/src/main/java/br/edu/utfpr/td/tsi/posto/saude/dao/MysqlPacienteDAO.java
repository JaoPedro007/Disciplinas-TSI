package br.edu.utfpr.td.tsi.posto.saude.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;

@Component
public class MysqlPacienteDAO implements PacienteDAO {
	
	@Autowired
	private DataSource dataSource;

	@Override
	public Long inserir(Paciente p) {
		long idPaciente = 0;
		String sql = "insert into paciente (nome, sobrenome, data_nascimento) values (?, ?, ?)";
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setString(1, p.getNome());
			preparedStatement.setString(2, p.getSobrenome());
			preparedStatement.setDate(3, Date.valueOf(p.getDataNascimento()));
			idPaciente = preparedStatement.executeUpdate();

			conn.close();
			preparedStatement.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Long.valueOf(idPaciente);
	}

	@Override
	public void atualizar(Long id, Paciente p) {
		//TODO
	}

	@Override
	public void remover(Long id) {
		//TODO

	}

	@Override
	public List<Paciente> listarTodos() {
		ArrayList<Paciente> pacientes = new ArrayList<>();
		try {
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select idPaciente, nome, sobrenome, data_nascimento from paciente");
			while (rs.next()) {
				Long id = rs.getLong(1);
				String nome = rs.getString(2);
				String sobrenome = rs.getString(3);
				LocalDate datanascimento = rs.getDate(4).toLocalDate();
				pacientes.add(new Paciente(id, nome, sobrenome, datanascimento));
			}
			conn.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pacientes;
	}

	@Override
	public Paciente procurar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

