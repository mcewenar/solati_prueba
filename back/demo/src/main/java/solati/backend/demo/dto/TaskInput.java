package solati.backend.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import solati.backend.demo.domain.TaskStatus;

public record TaskInput(
        @NotBlank @Size(max = 150) String title,
        @Size(max = 500) String description,
        TaskStatus status
) {
}
