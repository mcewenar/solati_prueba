package solati.backend.demo.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import solati.backend.demo.dto.TaskInput;
import solati.backend.demo.dto.TaskOutput;

import solati.backend.demo.domain.TaskStatus;
import solati.backend.demo.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@CrossOrigin(origins = "http://127.0.0.1:4200/")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<TaskOutput> getAll(@RequestParam(required = false) TaskStatus status) {
        return service.findAll(status);
    }

    @GetMapping("/{id}")
    public TaskOutput getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskOutput create(@Valid @RequestBody TaskInput request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public TaskOutput update(@PathVariable Long id,
                               @Valid @RequestBody TaskInput request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
