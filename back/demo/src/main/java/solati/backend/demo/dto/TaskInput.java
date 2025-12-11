package solati.backend.demo.dto;

import jakarta.validation.constraints.NotBlank;
import solati.backend.demo.domain.TaskStatus;

public record TaskInput(
        @NotBlank String title,
        @NotBlank String description,
        TaskStatus status
) {
}


