package br.edu.utfpr.td.tsi.posto.saude.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Bairro;

@Component
public class RDBBairroDao implements BairroDao {

	@Override
	public void cadastrar(Bairro bairro) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterar(long id, Bairro bairro) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Bairro> listar() {
		ArrayList<String> bairros = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/posto-saude", "root", "mysql");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select idBairro, nome from bairro");
			while (rs.next()) {
				long idBairro = rs.getLong(1);
				String nome = rs.getString(2);
				
				Bairro b = new Bairro();
				b.setId(idBairro);
				b.setNome(nome);
				
				bairros.add(b); 
			}

			conn.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bairros;
	}

}
