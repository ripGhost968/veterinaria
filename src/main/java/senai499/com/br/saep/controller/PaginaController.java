package senai499.com.br.saep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import senai499.com.br.saep.repository.ServicosRepository;

@Controller
public class PaginaController {
    private final ServicosRepository servicos;
    public PaginaController(ServicosRepository servicos){this.servicos=servicos;}
    @GetMapping("/") public String inicio(){return "index";}
    @GetMapping("/sobre") public String sobre(){return "sobre";}
    @GetMapping("/servicos") public String servicos(Model model){model.addAttribute("servicos",servicos.findByAtivoTrueOrderByNomeAsc());return "servicos";}
    @GetMapping("/modelo") public String modelo(){return "modelo";}
    @GetMapping("/login") public String login(){return "login";}
}
