package senai499.com.br.saep.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class ClientesModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @OneToOne(optional = false) @JoinColumn(name = "usuario_id", nullable = false, unique = true) private UsuariosModel usuario;
    @Column(length = 20) private String cpf;
    @Column(length = 20) private String telefone;
    @Column(length = 120) private String endereco;
    protected ClientesModel() {}
    public ClientesModel(UsuariosModel usuario, String cpf, String telefone, String endereco){this.usuario=usuario;this.cpf=cpf;this.telefone=telefone;this.endereco=endereco;}
    public Long getId(){return id;} public UsuariosModel getUsuario(){return usuario;} public String getCpf(){return cpf;} public String getTelefone(){return telefone;} public String getEndereco(){return endereco;}
    public void setCpf(String v){cpf=v;} public void setTelefone(String v){telefone=v;} public void setEndereco(String v){endereco=v;}
}
