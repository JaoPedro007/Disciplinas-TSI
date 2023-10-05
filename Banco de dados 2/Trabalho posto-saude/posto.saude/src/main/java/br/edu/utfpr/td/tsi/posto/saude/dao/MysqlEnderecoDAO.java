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
import br.edu.utfpr.td.tsi.posto.saude.modelo.Endereco;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;

@Component
public class MysqlEnderecoDAO implements EnderecoDAO {

	@Autowired
	private DataSource dataSource;

	@Override
	public void inserir(Endereco endereco) {
		String sql = "insert into endereco (logradouro, numero, cep, complemento, paciente_id, bairro_id) values (?, ?, ?, ?, ?, ?)";
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setString(1, endereco.getLogradouro());
			preparedStatement.setString(2, endereco.getNumero());
			preparedStatement.setString(3, endereco.getCep());
			preparedStatement.setString(4, endereco.getComplemento());
			preparedStatement.setLong(5, endereco.getBairro().getId());
			preparedStatement.setLong(6, endereco.getPaciente().getId());
			preparedStatement.executeUpdate();

			conn.close();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Endereco> listarTodos() {
	    ArrayList<Endereco> enderecos = new ArrayList<>();
	    try {
	        Connection conn = dataSource.getConnection();
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT e.id, e.logradouro, e.numero, e.cep, e.complemento, p.nome AS paciente_nome, b.nome AS bairro_nome FROM endereco e LEFT JOIN paciente p ON e.paciente_id = p.id LEFT JOIN bairro b ON e.bairro_id = b.id");
	        while (rs.next()) {
	            Long id = rs.getLong(1);
	            String logradouro = rs.getString(2);
	            String numero = rs.getString(3);
	            String cep = rs.getString(4);
	            String complemento = rs.getString(5);
	            String paciente_nome = rs.getString("paciente_nome"); 
	            String bairro_nome = rs.getString("bairro_nome");

	            Paciente paciente = new Paciente();
	            Bairro bairro = new Bairro();
	            paciente.setNome(paciente_nome);
	            bairro.setNome(bairro_nome);

	            enderecos.add(new Endereco(id, logradouro, numero, cep, complemento, paciente, bairro));
	        }
	        conn.close();
	        stmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return enderecos;
	}

	

	@Override
	public void atualizar(Endereco end) {
		// TODO
	}

	@Override
	public void remover(Long idEndereco) {
		// TODO

	}

	@Override
	public Endereco procurar(Long idEndereco) {
		// TODO Auto-generated method stub
		return null;
	}

}
