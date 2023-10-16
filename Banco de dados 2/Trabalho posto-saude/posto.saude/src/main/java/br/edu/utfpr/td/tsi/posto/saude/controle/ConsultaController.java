package br.edu.utfpr.td.tsi.posto.saude.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.utfpr.td.tsi.posto.saude.dao.ConsultaDAO;
import br.edu.utfpr.td.tsi.posto.saude.dao.MedicoDAO;
import br.edu.utfpr.td.tsi.posto.saude.dao.PacienteDAO;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Consulta;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Medico;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Status;

@Controller
public class ConsultaController {
	
	@Autowired
	private ConsultaDAO consultaDao;
	
	@Autowired
	private PacienteDAO pacienteDao;
	
	@Autowired
	private MedicoDAO medicoDao;
	
	
	@GetMapping(value = "/listarConsultas")
	public String listar(Model model) {
		List<Consulta> consultas = consultaDao.listarTodas();
		model.addAttribute("consultas", consultas);	
		return "listarConsultas";
	}

	@GetMapping(value = "/cadastrarConsulta")
	public String exibirPaginaConsulta(Model model) {
		List<Paciente> pacientes = pacienteDao.listarTodos();
		model.addAttribute("pacientes", pacientes);
		
		List<Medico> medicos = medicoDao.listarTodos();
		model.addAttribute( "medicos", medicos);
		return "cadastrarConsulta";
	}

	@PostMapping("/cadastrarConsulta")
	public String cadastrar(@ModelAttribute("e") Consulta consulta,@RequestParam("paciente.id") Long pacienteId,@RequestParam("medico.id") Long medicoId, Model model) {
	    Paciente paciente = new Paciente();
	    paciente.setId(pacienteId);

	    Medico medico = new Medico();
	    medico.setId(medicoId);

	    consulta.setPaciente(paciente);
	    consulta.setMedico(medico);
	    
	    if (pacienteDao.temConsultaAgendada(paciente.getId())) {
	        model.addAttribute("error", "O paciente já possui uma consulta agendada.");
	        return "cadastrarConsulta";
	    } else {
	        consulta.setStatus(Status.AGENDADA);
	        consultaDao.inserir(consulta);

	        return "redirect:/listarConsultas";
	    }
	}
	
	
	@PostMapping("/finalizarConsulta/{consultaId}")
	public String finalizarConsulta(@PathVariable Long consultaId, Model model) {
	    Consulta consulta = consultaDao.procurar(consultaId);

	    if(consulta.getStatus() == Status.AGENDADA) {
		    consulta.setStatus(Status.REALIZADA);	  
		    
		    consultaDao.atualizar(consulta.getId(), consulta);
		    
		    return "redirect:/listarConsultas";
	    }else{
	        model.addAttribute("error", "Não é possível Finalizar uma consulta Realizada ou Cancelada.");
	    	return "redirect:/listarConsultas";	    	
	    }

	}
	
	@PostMapping("/cancelarConsulta/{consultaId}")
	public String cancelarConsulta(@PathVariable Long consultaId) {
	    Consulta consulta = consultaDao.procurar(consultaId);
	    
	    if (consulta.getStatus() != Status.REALIZADA) {
	        consulta.setStatus(Status.CANCELADA);
	        consultaDao.atualizar(consultaId, consulta);
	    }

	    
	    return "redirect:/listarConsultas";
	}
	
	@GetMapping("/editarConsulta/{id}")
	public String exibirPaginaEdicaoConsulta(@PathVariable Long id, Model model) {
	    Consulta consulta = consultaDao.procurar(id);
	    
	    if(consulta.getStatus() == Status.AGENDADA) {
		    model.addAttribute("consulta", consulta);
		    
		    List<Paciente> pacientes = pacienteDao.listarTodos();
		    List<Medico> medicos = medicoDao.listarTodos();
		    model.addAttribute("pacientes", pacientes);
		    model.addAttribute("medicos", medicos);
		    return "editarConsulta"; 

	    }else {
	        model.addAttribute("error", "Não é possível editar uma consulta Realizada ou Cancelada.");
	        return "redirect:/listarConsultas";
	    }

	}

	@PostMapping("/salvarConsulta")
	public String editarConsulta(@ModelAttribute("consulta") Consulta consulta) {
	    consultaDao.editarConsulta(consulta.getId(), consulta);
	    return "redirect:/listarConsultas";
	}



	


}
