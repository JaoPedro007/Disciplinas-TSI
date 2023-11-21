package br.edu.utfpr.td.tsi.posto.saude.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.posto.saude.dao.PacienteDAO;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Status;


public class MysqlPacienteDAO implements PacienteDAO {

	@Autowired
	private DataSource dataSource;

	@Override
	public void inserir(Paciente p) {
		long id = 0;
		String sql = "insert into paciente (nome, sobrenome, telefone, data_nascimento) values (?, ?, ?, ?)";
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setString(1, p.getNome());
			preparedStatement.setString(2, p.getSobrenome());
			preparedStatement.setString(3, p.getTelefone());

			java.sql.Date sqlDate = java.sql.Date.valueOf(p.getData_nascimento());
			preparedStatement.setDate(4, sqlDate);

			id = preparedStatement.executeUpdate();

			conn.close();
			preparedStatement.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void atualizar(Long id, Paciente paciente) {
	    String sql = "UPDATE paciente SET nome = ?, sobrenome = ?, telefone = ?, data_nascimento = ? WHERE id = ?";
	    
	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
	        
	        preparedStatement.setString(1, paciente.getNome());
	        preparedStatement.setString(2, paciente.getSobrenome());
	        preparedStatement.setString(3, paciente.getTelefone());
	        
	        java.sql.Date sqlDate = java.sql.Date.valueOf(paciente.getData_nascimento());
	        preparedStatement.setDate(4, sqlDate);
	        
	        preparedStatement.setLong(5, paciente.getId());
	        
	        preparedStatement.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void remover(Long id) {
	    String sql = "DELETE FROM paciente WHERE id = ?";
	    
	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
	        
	        preparedStatement.setLong(1, id);
	        
	        preparedStatement.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public List<Paciente> listarTodos() {
		List<Paciente> pacientes = new ArrayList<>();
		try {
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT p.id, p.nome, p.sobrenome, p.telefone, p.data_nascimento FROM paciente p");
			while (rs.next()) {
				Long id = rs.getLong("id");
				String nome = rs.getString("nome");
				String sobrenome = rs.getString("sobrenome");
				String telefone = rs.getString("telefone");

				Date dataNascimentoDate = rs.getDate("data_nascimento");
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
	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement stmt = conn.prepareStatement("SELECT * FROM paciente WHERE id = ?")) {
	        stmt.setLong(1, id);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            Long pacienteId = rs.getLong("id");
	            String nome = rs.getString("nome");
	            String sobrenome = rs.getString("sobrenome");
	            String telefone = rs.getString("telefone");
	            Date data_nascimento = rs.getDate("data_nascimento");
	            
	            LocalDate dataNascimento = data_nascimento.toLocalDate();
	            
	            return (new Paciente(pacienteId, nome, sobrenome, telefone, dataNascimento));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null;
	}
	
	@Override
	public boolean temConsultaAgendada(Long pacienteId) {
	    try {
	        Connection conn = dataSource.getConnection();
	        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM consulta WHERE paciente_id = ? AND status = ?");
	        stmt.setLong(1, pacienteId);
	        stmt.setString(2, Status.AGENDADA.name());
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0;
	        }

	        conn.close();
	        stmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}


	
}
