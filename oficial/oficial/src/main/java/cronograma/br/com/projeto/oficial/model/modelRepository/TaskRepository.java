package cronograma.br.com.projeto.oficial.model.modelRepository;

import cronograma.br.com.projeto.oficial.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>{

    @Query("SELECT ts FROM Task ts WHERE ts.type = :tipoTarefa ORDER BY ts.type")
    List<Task> findForType(@Param("tipoTarefa") String tipoTarefa);

}
