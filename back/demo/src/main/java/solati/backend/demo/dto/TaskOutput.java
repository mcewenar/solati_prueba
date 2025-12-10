package solati.backend.demo.dto;

import solati.backend.demo.domain.TaskStatus;

public record TaskOutput(
        Long id,
        String title,
        String description,
        TaskStatus status
) {
}
