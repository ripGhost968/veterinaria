package senai499.com.br.saep.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
public class AgendamentosModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional = false) @JoinColumn(name = "usuario_id", nullable = false) private UsuariosModel cliente;
    @ManyToOne(optional = false) @JoinColumn(name = "servico_id", nullable = false) private ServicosModel servico;
    @FutureOrPresent @Column(nullable = false) private LocalDateTime dataHora;
    @Column(nullable = false, length = 100) private String nomePet;
    @Column(length = 500) private String observacao;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private StatusAgendamento status = StatusAgendamento.PENDENTE;
    protected AgendamentosModel() {}
    public AgendamentosModel(UsuariosModel cliente,ServicosModel servico,LocalDateTime dataHora,String nomePet,String observacao){this.cliente=cliente;this.servico=servico;this.dataHora=dataHora;this.nomePet=nomePet;this.observacao=observacao;}
    public Long getId(){return id;} public UsuariosModel getCliente(){return cliente;} public ServicosModel getServico(){return servico;} public LocalDateTime getDataHora(){return dataHora;} public String getNomePet(){return nomePet;} public String getObservacao(){return observacao;} public StatusAgendamento getStatus(){return status;}
    public void setStatus(StatusAgendamento v){status=v;}
}
