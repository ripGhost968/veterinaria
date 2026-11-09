package senai499.com.br.saep.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import senai499.com.br.saep.model.ClientesModel;
import senai499.com.br.saep.model.PerfilUsuario;
import senai499.com.br.saep.model.UsuariosModel;
import senai499.com.br.saep.repository.ClientesRepository;
import senai499.com.br.saep.repository.UsuariosRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {
    private final UsuariosRepository usuarios; private final ClientesRepository clientes; private final PasswordEncoder encoder;
    public CadastroController(UsuariosRepository usuarios,ClientesRepository clientes,PasswordEncoder encoder){this.usuarios=usuarios;this.clientes=clientes;this.encoder=encoder;}
    @GetMapping public String formulario(Model model){model.addAttribute("form",new CadastroForm());return "cadastro";}
    @PostMapping public String cadastrar(@Valid @ModelAttribute("form") CadastroForm form, BindingResult result, Model model){
        if(!form.getSenha().equals(form.getConfirmarSenha())) result.rejectValue("confirmarSenha","senha.diferente","As senhas não conferem.");
        if(usuarios.existsByEmail(form.getEmail())) result.rejectValue("email","email.existente","Este e-mail já possui cadastro.");
        if(result.hasErrors()) return "cadastro";
        UsuariosModel usuario=usuarios.save(new UsuariosModel(form.getNome().trim(),form.getEmail().trim().toLowerCase(),encoder.encode(form.getSenha()),PerfilUsuario.CLIENTE));
        clientes.save(new ClientesModel(usuario,form.getCpf(),form.getTelefone(),form.getEndereco()));
        return "redirect:/login?cadastro";
    }
    public static class CadastroForm {
        @NotBlank @Size(max=120) private String nome;
        @NotBlank @Email @Size(max=160) private String email;
        @NotBlank @Size(min=8,max=100) private String senha;
        @NotBlank private String confirmarSenha;
        @Size(max=20) private String cpf; @Size(max=20) private String telefone; @Size(max=120) private String endereco;
        public String getNome(){return nome;} public void setNome(String v){nome=v;} public String getEmail(){return email;} public void setEmail(String v){email=v;} public String getSenha(){return senha;} public void setSenha(String v){senha=v;} public String getConfirmarSenha(){return confirmarSenha;} public void setConfirmarSenha(String v){confirmarSenha=v;} public String getCpf(){return cpf;} public void setCpf(String v){cpf=v;} public String getTelefone(){return telefone;} public void setTelefone(String v){telefone=v;} public String getEndereco(){return endereco;} public void setEndereco(String v){endereco=v;}
    }
}
