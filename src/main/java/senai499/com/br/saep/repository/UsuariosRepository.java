package senai499.com.br.saep.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import senai499.com.br.saep.model.UsuariosModel;
import java.util.Optional;
public interface UsuariosRepository extends JpaRepository<UsuariosModel,Long> { Optional<UsuariosModel> findByEmail(String email); boolean existsByEmail(String email); }
