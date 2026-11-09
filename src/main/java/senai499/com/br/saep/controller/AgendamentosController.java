package senai499.com.br.saep.controller;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import senai499.com.br.saep.model.AgendamentosModel;
import senai499.com.br.saep.model.UsuariosModel;
import senai499.com.br.saep.repository.AgendamentosRepository;
import senai499.com.br.saep.repository.ServicosRepository;
import jakarta.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentosController {
    private final AgendamentosRepository agendamentos; private final ServicosRepository servicos;
    public AgendamentosController(AgendamentosRepository agendamentos,ServicosRepository servicos){this.agendamentos=agendamentos;this.servicos=servicos;}
    @GetMapping public String listar(@AuthenticationPrincipal UsuariosModel usuario,Model model){model.addAttribute("form",new AgendamentoForm());model.addAttribute("servicos",servicos.findByAtivoTrueOrderByNomeAsc());model.addAttribute("agendamentos",agendamentos.findByClienteEmailOrderByDataHoraDesc(usuario.getEmail()));return "agendamentos";}
    @PostMapping public String criar(@AuthenticationPrincipal UsuariosModel usuario,@Valid @ModelAttribute("form") AgendamentoForm form,BindingResult result,Model model){
        var servico=servicos.findById(form.getServicoId()).filter(s->s.isAtivo()).orElse(null);
        if(servico==null) result.rejectValue("servicoId","servico.invalido","Selecione um serviço válido.");
        if(result.hasErrors()){model.addAttribute("servicos",servicos.findByAtivoTrueOrderByNomeAsc());model.addAttribute("agendamentos",agendamentos.findByClienteEmailOrderByDataHoraDesc(usuario.getEmail()));return "agendamentos";}
        agendamentos.save(new AgendamentosModel(usuario,servico,form.getDataHora(),form.getNomePet().trim(),form.getObservacao()));
        return "redirect:/agendamentos?sucesso";
    }
    public static class AgendamentoForm {
        @NotNull private Long servicoId;
        @NotBlank private String nomePet;
        @NotNull @FutureOrPresent @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) private LocalDateTime dataHora;
        private String observacao;
        public Long getServicoId(){return servicoId;} public void setServicoId(Long v){servicoId=v;} public String getNomePet(){return nomePet;} public void setNomePet(String v){nomePet=v;} public LocalDateTime getDataHora(){return dataHora;} public void setDataHora(LocalDateTime v){dataHora=v;} public String getObservacao(){return observacao;} public void setObservacao(String v){observacao=v;}
    }
}
