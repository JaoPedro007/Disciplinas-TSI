package br.edu.utfpr.td.tsi.posto.saude.controle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.utfpr.td.tsi.posto.saude.dao.BairroRepository;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Bairro;

@Controller
public class BairroController {

	@Autowired
	BairroRepository bairroRepository;

	@GetMapping(value = "/listarbairros")
	public String listar(Model model) {
		Iterable<Bairro> bairros = bairroRepository.findAll();			
		model.addAttribute("bairros", bairros);	
		return "listarBairros";
	}

	@GetMapping(value = "/cadastrarbairro")
	public String exibirPaginaVadastro() {
		return "cadastrarBairro";
	}

	@PostMapping("/cadastrarBairro")
	public String cadastrar(Bairro bairro) {
		bairroRepository.save(bairro);
		return "redirect:/listarbairros";
	}

}
