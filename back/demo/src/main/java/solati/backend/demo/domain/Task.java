package solati.backend.demo.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa una tarea dentro del sistema.
 *
 * <p>Esta clase está mapeada a la tabla {@code task} en la base de datos mediante JPA.
 * Contiene los campos principales de una tarea:
 * <ul>
 *     <li>id — Identificador único generado automáticamente</li>
 *     <li>title — Título de la tarea (obligatorio)</li>
 *     <li>description — Descripción detallada de la tarea</li>
 *     <li>status—Estado de la tarea (PENDING, COMPLETED)</li>
 * </ul>
 *
 * <p>Se utiliza Lombok para reducir código repetitivo:
 * <ul>
 *     <li>{@link Data} genera getters, setters, equals(), hashCode() y toString()</li>
 *     <li>{@link NoArgsConstructor} crea un constructor vacío</li>
 *     <li>{@link AllArgsConstructor} crea un constructor con todos los campos</li>
 * </ul>
 *
 * <p>Esta entidad es utilizada por {@link solati.backend.demo.repository.TaskRepository}
 * para interactuar con la base de datos y por {@link solati.backend.demo.service.TaskService}
 * para la lógica de negocio.</p>
 */

@Entity //Bean de persistencia de datos de Jpa data
@Table(name = "task") // Nombre de la tabla en la base de datos
@Data
@NoArgsConstructor
@AllArgsConstructor // Constructor con todos los campos
public class Task {


    //Identificador único de la tarea.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Es un campo obligatorio y se limita a 255 caracteres
    @Column(nullable = false, length = 255)
    private String title;

    //Descripción detallada de la tarea. TEXT peermite descripciones largas
    @Column(columnDefinition = "TEXT")
    private String description;

    // Estado actual de la tarea. Se almacena como un texto corto (máximo 20 caracteres)
    @Column(nullable = false, length = 20)
    private String status;
}

