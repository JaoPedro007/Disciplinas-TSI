package br.edu.utfpr.td.tsi.posto.saude.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Consulta;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Medico;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Status;

@Component
public class MysqlConsultaDAO implements ConsultaDAO {

	@Autowired
	private DataSource dataSource;

	@Override
	public Long inserir(Consulta c) {
		long id = 0;
		String sql = "insert into consulta (data_consulta, paciente_id, medico_id, descricao, status) values (?, ?, ?, ?, ?)";
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			
	        preparedStatement.setTimestamp(1, new Timestamp(c.getData_consulta().getTime()));
			preparedStatement.setLong(2, c.getPaciente().getId());
			preparedStatement.setLong(3, c.getMedico().getId());
			preparedStatement.setString(4, c.getDescricao());
			preparedStatement.setString(5, c.getStatus().name());

			id = preparedStatement.executeUpdate();

			conn.close();
			preparedStatement.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Long.valueOf(id);
	}

	@Override
	public void remover(Long id) {
		// TODO

	}

	@Override
	public List<Consulta> listarTodas() {
	    ArrayList<Consulta> consultas = new ArrayList<>();
	    try {
	        Connection conn = dataSource.getConnection();
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT c.id, c.data_consulta, p.nome, p.sobrenome, m.nome, c.descricao, c.status FROM CONSULTA c INNER JOIN PACIENTE p ON c.paciente_id = p.id INNER JOIN MEDICO m ON c.medico_id = m.id");
	        while (rs.next()) {
	        	
	        	
        	
	            Long id = rs.getLong(1);
	            Timestamp data_consulta = rs.getTimestamp(2);
	            String nomePaciente = rs.getString(3);
	            String sobrenomePaciente = rs.getString(4); 
	            String nomeMedico = rs.getString(5);
	            String descricao = rs.getString(6);
	            String statusConsulta = rs.getString(7);
	            
	            Status status = Status.valueOf(statusConsulta);

	            Paciente paciente = new Paciente();
	            paciente.setNome(nomePaciente);
	            paciente.setSobrenome(sobrenomePaciente);

	            Medico medico = new Medico();
	            medico.setNome(nomeMedico);

	            consultas.add(new Consulta(id, data_consulta, paciente, medico, descricao, status));
	        }
	        conn.close();
	        stmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return consultas;
	}


	@Override
	public void atualizar(Long id, Consulta c) {
	    String sql = "UPDATE consulta SET data_consulta = ?, paciente_id = ?, medico_id = ?, descricao = ?, status = ? WHERE id = ?";
	    try {
	        Connection conn = dataSource.getConnection();
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        
	        preparedStatement.setTimestamp(1, new Timestamp(c.getData_consulta().getTime()));
	        preparedStatement.setLong(2, c.getPaciente().getId());
	        preparedStatement.setLong(3, c.getMedico().getId());
	        preparedStatement.setString(4, c.getDescricao());
	        preparedStatement.setString(5, c.getStatus().name());
	        preparedStatement.setLong(6, id);

	        preparedStatement.executeUpdate();

	        conn.close();
	        preparedStatement.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void editarConsulta(Long id, Consulta c) {
	    String sql = "UPDATE consulta SET data_consulta = ?, paciente_id = ?, medico_id = ?, descricao = ? WHERE id = ?";
	    try {
	        Connection conn = dataSource.getConnection();
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        
	        preparedStatement.setTimestamp(1, new Timestamp(c.getData_consulta().getTime()));
	        preparedStatement.setLong(2, c.getPaciente().getId());
	        preparedStatement.setLong(3, c.getMedico().getId());
	        preparedStatement.setString(4, c.getDescricao());
	        preparedStatement.setLong(5, id);

	        preparedStatement.executeUpdate();

	        conn.close();
	        preparedStatement.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public Consulta procurar(Long id) {
	    Consulta consulta = null;
	    String sql = "SELECT data_consulta, paciente_id, medico_id, descricao, status FROM consulta WHERE id = ?";
	    try {
	        Connection conn = dataSource.getConnection();
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setLong(1, id);
	        ResultSet rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	            Timestamp dataConsulta = rs.getTimestamp("data_consulta");
	            Long pacienteId = rs.getLong("paciente_id");
	            Long medicoId = rs.getLong("medico_id");
	            String descricao = rs.getString("descricao");
	            String statusConsulta = rs.getString("status");

	            Status status = Status.valueOf(statusConsulta);

	            Paciente paciente = new Paciente();
	            paciente.setId(pacienteId);

	            Medico medico = new Medico();
	            medico.setId(medicoId);

	            consulta = new Consulta(id, new Date(dataConsulta.getTime()), paciente, medico, descricao, status);
	        }

	        conn.close();
	        preparedStatement.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return consulta;
	}



}
