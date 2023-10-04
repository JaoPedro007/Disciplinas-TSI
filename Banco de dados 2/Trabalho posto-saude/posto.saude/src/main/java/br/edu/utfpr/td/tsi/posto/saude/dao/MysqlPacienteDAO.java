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
	    long id = 0;
	    String sql = "insert into paciente (nome, sobrenome, telefone, data_nascimento) values (?, ?, ?, ?)";
	    try {
	        Connection conn = dataSource.getConnection();
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);

	        preparedStatement.setString(1, p.getNome());
	        preparedStatement.setString(2, p.getSobrenome());
	        preparedStatement.setString(3, p.getTelefone());
	        
	        // Converter LocalDate para java.sql.Date
	        java.sql.Date sqlDate = java.sql.Date.valueOf(p.getData_nascimento());
	        preparedStatement.setDate(4, sqlDate);

	        id = preparedStatement.executeUpdate();

	        conn.close();
	        preparedStatement.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return Long.valueOf(id);
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
			ResultSet rs = stmt.executeQuery("select id, nome, sobrenome, telefone, data_nascimento from paciente");
			while (rs.next()) {
				Long id = rs.getLong(1);
				String nome = rs.getString(2);
				String sobrenome = rs.getString(3);
				String telefone = rs.getString(4);
	            Date dataNascimentoDate = rs.getDate(5);
	            LocalDate dataNascimento = dataNascimentoDate.toLocalDate();
	            pacientes.add(new Paciente(id, nome, sobrenome, telefone, dataNascimento));
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

