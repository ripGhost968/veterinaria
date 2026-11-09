package senai499.com.br.saep.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import senai499.com.br.saep.model.ServicosModel;
import senai499.com.br.saep.repository.ServicosRepository;
import java.math.BigDecimal;

@Configuration
public class DadosIniciais {
    @Bean CommandLineRunner criarServicosPadrao(ServicosRepository servicos){return args -> {
        if(servicos.count()==0){
            servicos.save(new ServicosModel("Consulta veterinária","Avaliação clínica e orientação para os cuidados do seu pet.",new BigDecimal("90.00")));
            servicos.save(new ServicosModel("Vacinação","Aplicação de vacinas conforme avaliação e calendário do animal.",new BigDecimal("65.00")));
            servicos.save(new ServicosModel("Retorno","Acompanhamento do tratamento e revisão clínica.",new BigDecimal("50.00")));
        }
    };}
}
