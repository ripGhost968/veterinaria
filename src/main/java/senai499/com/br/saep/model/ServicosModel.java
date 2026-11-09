package senai499.com.br.saep.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
@Table(name = "servicos")
public class ServicosModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank @Column(nullable = false, length = 100) private String nome;
    @Column(length = 500) private String descricao;
    @PositiveOrZero @Column(nullable = false, precision = 10, scale = 2) private BigDecimal preco = BigDecimal.ZERO;
    @Column(nullable = false) private boolean ativo = true;
    protected ServicosModel() {}
    public ServicosModel(String nome,String descricao,BigDecimal preco){this.nome=nome;this.descricao=descricao;this.preco=preco;}
    public Long getId(){return id;} public String getNome(){return nome;} public String getDescricao(){return descricao;} public BigDecimal getPreco(){return preco;} public boolean isAtivo(){return ativo;}
    public void setNome(String v){nome=v;} public void setDescricao(String v){descricao=v;} public void setPreco(BigDecimal v){preco=v;} public void setAtivo(boolean v){ativo=v;}
}
