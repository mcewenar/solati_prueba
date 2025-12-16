package solati.backend.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solati.backend.demo.dto.TaskInput;
import solati.backend.demo.dto.TaskOutput;
import solati.backend.demo.exception.TaskNotFoundException;
import solati.backend.demo.domain.Task;
import solati.backend.demo.domain.TaskStatus;
import solati.backend.demo.repository.TaskRepository;

import java.util.List;

/**
 * Servicio encargado de gestionar la lógica de negocio del recurso Task.
 *
 * <p>Este servicio implementa:
 * <ul>
 *     <li>Consulta de tareas con o sin filtros</li>
 *     <li>Búsqueda de tareas por id</li>
 *     <li>Creación de nuevas tareas</li>
 *     <li>Actualización de tareas existentes</li>
 *     <li>Eliminación de tareas</li>
 * </ul>
 *
 * <p>El patrón aplicado es Controller → Service → Repository, manteniendo separada la lógica
 * de negocio de la capa de acceso a datos.</p>
 *
 * <p>La anotación {@link Transactional} garantiza que cada operación se ejecute dentro de
 * un contexto transaccional.</p>
 */
@Service
@Transactional
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    //Obtiene todas las tareas, con opción de filtrar por estado.
    public List<TaskOutput> findAll(TaskStatus status) {
        List<Task> tasks = (status == null) ? repository.findAll():repository.findByStatus(status);

        return tasks.stream()
                .map(this::toResponse)
                .toList();
    }
    //Busca una tarea por su id.
    public TaskOutput findById(Long id){
        Task task = repository.findById(id)
                //Manejo de excepciones
                .orElseThrow(() -> new TaskNotFoundException(id));
        return toResponse(task);
    }

    //Crea una nueva tarea en el sistema. Si el status no se envía, por defecto se usa pending
    public TaskOutput create(TaskInput request) {
        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status() != null ? request.status() : TaskStatus.PENDING);
        return toResponse(repository.save(task));
    }


    //Actualiza una tarea existente.
    public TaskOutput update(Long id, TaskInput request) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status() != null ? request.status() : TaskStatus.PENDING);
        return toResponse(repository.save(task));
    }

    //Elimina una tarea por su id.
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        repository.deleteById(id);
    }

    //CONSTRUCTOR DE RESPUESTAS DTO DE SALIDA.}
    //Podría usar patrón builder
    private TaskOutput toResponse(Task task) {
        return new TaskOutput(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus()
        );
    }
}
