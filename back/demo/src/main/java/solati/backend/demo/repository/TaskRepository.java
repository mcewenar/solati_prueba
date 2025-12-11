package solati.backend.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solati.backend.demo.domain.Task;
import solati.backend.demo.domain.TaskStatus;

import java.util.List;

/**
 * Repositorio JPA para la entidad {@link Task}.
 *
 * <p>Extiende {@link JpaRepository}, lo que proporciona automáticamente
 * operaciones CRUD completas sin necesidad de implementarlas manualmente:
 * <ul>
 *     <li>save()</li>
 *     <li>findById()</li>
 *     <li>findAll()</li>
 *     <li>deleteById()</li>
 *     <li>existsById()</li>
 * </ul>
 *
 * <p>Además, define un método personalizado para consultar tareas
 * según su estado ({@link TaskStatus}). Spring Data JPA generará
 * automáticamente la implementación basada en el nombre del método.</p>
 *
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    //busca todas las tareas cuyo estado coincida con el valor proporcionado. Además, spring data mira el nombre del métod
    //y hace una consulta a partir de este findBy + Status
    List<Task> findByStatus(TaskStatus status);

}
