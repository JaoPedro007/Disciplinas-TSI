package br.edu.utfpr.td.tsi.posto.saude.dao;

import org.springframework.data.repository.CrudRepository;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;

public interface PacienteRepository extends CrudRepository<Paciente, String> {

}


