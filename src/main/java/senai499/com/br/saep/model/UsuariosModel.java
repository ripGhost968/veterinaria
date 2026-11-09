package senai499.com.br.saep.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class UsuariosModel implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, length = 120) private String nome;
    @Column(nullable = false, unique = true, length = 160) private String email;
    @Column(nullable = false) private String senha;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private PerfilUsuario perfil = PerfilUsuario.CLIENTE;
    @Column(nullable = false) private boolean ativo = true;
    protected UsuariosModel() {}
    public UsuariosModel(String nome, String email, String senha, PerfilUsuario perfil) { this.nome=nome; this.email=email; this.senha=senha; this.perfil=perfil; }
    public Long getId(){return id;} public String getNome(){return nome;} public void setNome(String v){nome=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;} public void setSenha(String v){senha=v;}
    public PerfilUsuario getPerfil(){return perfil;} public void setPerfil(PerfilUsuario v){perfil=v;} public boolean isAtivo(){return ativo;} public void setAtivo(boolean v){ativo=v;}
    @Override public Collection<? extends GrantedAuthority> getAuthorities(){return List.of(new SimpleGrantedAuthority("ROLE_"+perfil.name()));}
    @Override public String getPassword(){return senha;} @Override public String getUsername(){return email;}
    @Override public boolean isAccountNonExpired(){return true;} @Override public boolean isAccountNonLocked(){return ativo;}
    @Override public boolean isCredentialsNonExpired(){return true;} @Override public boolean isEnabled(){return ativo;}
}
