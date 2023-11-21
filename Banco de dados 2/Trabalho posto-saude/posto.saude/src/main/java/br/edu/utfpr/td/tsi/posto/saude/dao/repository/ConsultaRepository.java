package br.edu.utfpr.td.tsi.posto.saude.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Consulta;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Status;

public interface ConsultaRepository extends CrudRepository<Consulta, Long> {

	@Query("SELECT COUNT(c) FROM Consulta c WHERE c.paciente.id = :pacienteId AND c.status = :statusAgendada")
	int countConsultasAgendadas(@Param("pacienteId") Long pacienteId, @Param("statusAgendada") Status AGENDADA);

}
