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

import br.edu.utfpr.td.tsi.posto.saude.modelo.Especialidade;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Medico;

@Component
public class MysqlMedico implements MedicoDAO {

	@Autowired
	private DataSource dataSource;

	@Override
	public Long inserir(Medico m) {
		long id = 0;
		String sql = "insert into medico (nome, sobrenome, crm, telefone, cpf, especialidade_id) values (?, ?, ?, ?, ?, ?)";
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setString(1, m.getNome());
			preparedStatement.setString(2, m.getSobrenome());
			preparedStatement.setString(3, m.getCrm());
			preparedStatement.setString(4, m.getTelefone());
			preparedStatement.setString(5, m.getCpf());
			preparedStatement.setLong(6, m.getEspecialidade().getId());

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
	public List<Medico> listarTodos() {
		ArrayList<Medico> medicos = new ArrayList<>();
		try {
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT m.id, m.nome, m.sobrenome, m.crm, m.telefone, m.cpf, m.especialidade_id, e.descricao FROM medico m LEFT JOIN especialidade e ON m.especialidade_id = e.id");
			while (rs.next()) {
				Long id = rs.getLong(1);
				String nome = rs.getString(2);
				String sobrenome = rs.getString(3);
				String crm = rs.getString(4);
				String telefone = rs.getString(5);
				String cpf = rs.getString(6);
	            Long especialidadeId = rs.getLong(7);
	            String especialidadeDescricao = rs.getString(8);
	            Especialidade especialidade = new Especialidade();
	            especialidade.setId(especialidadeId);
	            especialidade.setDescricao(especialidadeDescricao);
	            
				medicos.add(new Medico(id, nome, sobrenome, crm, telefone, cpf, especialidade));
			}
			conn.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medicos;
	}

	@Override
	public void atualizar(Long id, Medico m) {
		// TODO Auto-generated method stub

	}

	@Override
	public Medico procurar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
