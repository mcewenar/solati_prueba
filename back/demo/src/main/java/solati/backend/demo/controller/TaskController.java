package solati.backend.demo.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import solati.backend.demo.dto.TaskInput;
import solati.backend.demo.dto.TaskOutput;

import solati.backend.demo.domain.TaskStatus;
import solati.backend.demo.service.TaskService;

import java.util.List;


/**
 * Controlador REST que gestiona las operaciones CRUD del recurso "Task".
 *
 * <p>Expone endpoints para:
 * <ul>
 *     <li>Listar tareas con o sin filtro por estado</li>
 *     <li>Obtener una tarea por su ID</li>
 *     <li>Crear una nueva tarea</li>
 *     <li>Actualizar una tarea existente</li>
 *     <li>Eliminar una tarea</li>
 * </ul>
 *
 * <p>Este controlador sigue el patrón estándar Controller → Service → Repository, delegando
 * toda la lógica de negocio en {@link TaskService}.</p>
 */
@RestController
@RequestMapping("/api/task")  // Ruta base
@CrossOrigin(origins = "http://127.0.0.1:4200/") // Permite peticiones desde Angular (CORS)
public class TaskController {

    private final TaskService service;

    //Inyección de dependencia
    public TaskController(TaskService service) {
        this.service = service;
    }

    //Obtiene todas las tareas registradas
    @GetMapping
    public List<TaskOutput> getAll(@RequestParam(required = false) TaskStatus status) {
        return service.findAll(status);
    }

    //Obtiene una tarea por su identificador único.
    @GetMapping("/{id}")
    public TaskOutput getById(@PathVariable Long id) {
        return service.findById(id);
    }

    // Crea una nueva tarea en el sistema.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskOutput create(@Valid @RequestBody TaskInput request) {
        return service.create(request);
    }

    // Actualiza una tarea existente.
    @PutMapping("/{id}")
    public TaskOutput update(@PathVariable Long id,
                               @Valid @RequestBody TaskInput request) {
        return service.update(id, request);
    }
    // Elimina una tarea por su ID.
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
