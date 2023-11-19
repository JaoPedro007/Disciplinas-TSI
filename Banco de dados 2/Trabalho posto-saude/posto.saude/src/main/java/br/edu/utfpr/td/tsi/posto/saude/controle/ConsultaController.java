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

import br.edu.utfpr.td.tsi.posto.saude.modelo.Consulta;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Medico;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Status;
import br.edu.utfpr.td.tsi.posto.saude.service.ConsultaServiceImpl;
import br.edu.utfpr.td.tsi.posto.saude.service.MedicoServiceImpl;
import br.edu.utfpr.td.tsi.posto.saude.service.PacienteServiceImpl;

@Controller
public class ConsultaController {
	
	@Autowired
	private ConsultaServiceImpl consultaService;
	
	@Autowired
	private MedicoServiceImpl medicoService;
	
	@Autowired
	private PacienteServiceImpl pacienteService;
	
	
	@GetMapping(value = "/listarConsultas")
	public String listar(Model model) {
		List<Consulta> consultas = consultaService.listarTodas();
		model.addAttribute("consultas", consultas);	
		return "listarConsultas";
	}

	@GetMapping(value = "/cadastrarConsulta")
	public String exibirPaginaConsulta(Model model) {
		List<Paciente> pacientes = pacienteService.listarTodos();
		model.addAttribute("pacientes", pacientes);
		
		List<Medico> medicos = medicoService.listarTodos();
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
	    
	    if (pacienteService.temConsultaAgendada(paciente.getId())) {
	        model.addAttribute("error", "O paciente já possui uma consulta agendada.");
	        return "cadastrarConsulta";
	    } else {
	        consulta.setStatus(Status.AGENDADA);
	        consultaService.inserir(consulta);

	        return "redirect:/listarConsultas";
	    }
	}
	
	
	@PostMapping("/finalizarConsulta/{consultaId}")
	public String finalizarConsulta(@PathVariable Long consultaId, Model model) {
	    Consulta consulta = consultaService.procurar(consultaId);

	    if(consulta.getStatus() == Status.AGENDADA) {
		    consulta.setStatus(Status.REALIZADA);	  
		    
		    consultaService.atualizar(consulta.getId(), consulta);
		    
		    return "redirect:/listarConsultas";
	    }else{
	        model.addAttribute("error", "Não é possível Finalizar uma consulta Realizada ou Cancelada.");
	    	return "redirect:/listarConsultas";	    	
	    }

	}
	
	@PostMapping("/cancelarConsulta/{consultaId}")
	public String cancelarConsulta(@PathVariable Long consultaId) {
	    Consulta consulta = consultaService.procurar(consultaId);
	    
	    if (consulta.getStatus() != Status.REALIZADA) {
	        consulta.setStatus(Status.CANCELADA);
	        consultaService.atualizar(consultaId, consulta);
	    }

	    
	    return "redirect:/listarConsultas";
	}
	
	@GetMapping("/editarConsulta/{id}")
	public String exibirPaginaEdicaoConsulta(@PathVariable Long id, Model model) {
	    Consulta consulta = consultaService.procurar(id);
	    
	    if(consulta.getStatus() == Status.AGENDADA) {
		    model.addAttribute("consulta", consulta);
		    
		    List<Paciente> pacientes = pacienteService.listarTodos();
		    List<Medico> medicos = medicoService.listarTodos();
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
		consultaService.editarConsulta(consulta.getId(), consulta);
	    return "redirect:/listarConsultas";
	}



	


}
