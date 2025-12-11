package solati.backend.demo.dto;

public record TaskOutput(
        Long id,
        String title,
        String description,
        String status
) {
}


