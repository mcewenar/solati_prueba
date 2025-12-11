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

@Service
@Transactional
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<TaskOutput> findAll(TaskStatus status) {
        List<Task> tasks = (status == null) ? repository.findAll()  : repository.findByStatus(status);

        return tasks.stream()
                .map(this::toResponse)
                .toList();
    }

    public TaskOutput findById(Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return toResponse(task);
    }

    public TaskOutput create(TaskInput request) {
        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(String.valueOf(request.status() != null ? request.status() : TaskStatus.PENDING));
        return toResponse(repository.save(task));
    }

    public TaskOutput update(Long id, TaskInput request) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(String.valueOf(request.status() != null ? request.status() : TaskStatus.PENDING));
        return toResponse(repository.save(task));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private TaskOutput toResponse(Task task) {
        return new TaskOutput(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus()
        );
    }
}
