package solati.backend.demo.exception;


/**
 * Excepción personalizada utilizada cuando una tarea no es encontrada en el sistema.
 *
 * <p>Esta excepción extiende {@link RuntimeException}, lo que permite lanzarla sin necesidad
 * de declararla explícitamente en los métodos (unchecked exception).</p>
 */
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Tarea no encontrada "+ id);
    }

    //Podría agregar más tipos de excepciones. Adicional, crear un centralizador para manejar de forma contralada cada tipo de error
    //Y devolverlo con una estructura consistente para trazabilidad y manejo oportuno de logs de errores.
}
