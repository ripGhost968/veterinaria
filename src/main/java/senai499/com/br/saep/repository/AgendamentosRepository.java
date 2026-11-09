package senai499.com.br.saep.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import senai499.com.br.saep.model.AgendamentosModel;
import java.util.List;
public interface AgendamentosRepository extends JpaRepository<AgendamentosModel,Long> { List<AgendamentosModel> findByClienteEmailOrderByDataHoraDesc(String email); }
