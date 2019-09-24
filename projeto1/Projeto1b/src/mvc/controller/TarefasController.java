package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.model.Tarefa;
import mvc.model.DAO;

@Controller
public class TarefasController {
	@RequestMapping("/")
	public String execute() {
		System.out.println("Logica do MVC");
		return "info";
	}
	
	@RequestMapping("criaTarefa")
	public String form(){
		return"formTarefa";    
	}
	
	@RequestMapping("adicionaTarefa")
	public String adiciona(Tarefa tarefa) {
		DAO dao = new DAO();
		dao.adicionaDescricao(tarefa);
		return "adicionada";
	}
}
