package senai499.com.br.saep.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import senai499.com.br.saep.model.ServicosModel;
import java.util.List;
public interface ServicosRepository extends JpaRepository<ServicosModel,Long> { List<ServicosModel> findByAtivoTrueOrderByNomeAsc(); }
