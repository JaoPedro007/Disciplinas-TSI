package br.edu.utfpr.td.tsi.posto.saude.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.utfpr.td.tsi.posto.saude.dao.ConsultaDAO;
import br.edu.utfpr.td.tsi.posto.saude.dao.MedicoDAO;
import br.edu.utfpr.td.tsi.posto.saude.dao.PacienteDAO;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Consulta;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Medico;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;

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
	public String exibirPaginaCadastro(Model model) {
		List<Paciente> pacientes = pacienteDao.listarTodos();
		model.addAttribute("pacientes", pacientes);
		
		List<Medico> medicos = medicoDao.listarTodos();
		model.addAttribute( "medicos", medicos);
		return "cadastrarConsulta";
	}

	@PostMapping("/cadastrarConsulta")
	public String cadastrar(@ModelAttribute("e")Consulta consulta) {
		consultaDao.inserir(consulta);
		return "redirect:/listarConsultas";
	}

}
