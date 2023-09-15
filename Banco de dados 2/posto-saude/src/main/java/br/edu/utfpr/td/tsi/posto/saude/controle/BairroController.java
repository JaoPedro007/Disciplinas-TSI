package br.edu.utfpr.td.tsi.posto.saude.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.utfpr.td.tsi.posto.saude.dao.BairroDao;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Bairro;

@Controller
public class BairroController {
	@Autowired
	private BairroDao bairroDao;

	@GetMapping (value="bairros")
	public String exibirTabelaBairros(Model model) {
		
		List<Bairro> bairros = bairroDao.listar();
		model.addAttribute("listaBairros", bairros);
		
		return "exibirTabelaBairros";
	}

}
