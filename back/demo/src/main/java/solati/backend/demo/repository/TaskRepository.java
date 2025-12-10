package solati.backend.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solati.backend.demo.domain.Task;
import solati.backend.demo.domain.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);

}
