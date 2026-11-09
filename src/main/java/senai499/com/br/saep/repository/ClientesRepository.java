package senai499.com.br.saep.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import senai499.com.br.saep.model.ClientesModel;
public interface ClientesRepository extends JpaRepository<ClientesModel,Long> { }
