package solati.backend.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
/**
 * TODOs SON BEANS
 * Bean que define el servicio encargado de cargar los usuarios que pueden autenticarse.
 se utiliza un usuario en memoria para efectos de prueba.
 */

/**
 * Configuración central de seguridad para la aplicación.
 *
 * <p>Esta clase define:
 * <ul>
 *     <li>Un usuario en memoria para pruebas (ADMIN)</li>
 *     <li>La configuración de CORS para permitir peticiones desde Angular</li>
 *     <li>El uso de HTTP Basic como mecanismo de autenticación</li>
 *     <li>Una política de sesiones STATLESS para una API REST</li>
 *     <li>Reglas de autorización para proteger los endpoints</li>
 * </ul>
 *
 * <p>La anotación {@link EnableMethodSecurity} habilita seguridad a nivel de métodos,
 * permitiendo el uso de anotaciones como {@code @PreAuthorize}.</p>
 */
@Configuration
@EnableMethodSecurity
public class SecurityBConfig {


    /**
     * Configura un {@link UserDetailsService} en memoria con un único usuario ADMIN.
     *
     * <p>Este método crea un usuario llamado {@code admin} con la contraseña {@code password}
     * codificada mediante el {@link PasswordEncoder} {@code BCrypt}. Este usuario es útil para
     * pruebas o entornos de desarrollo donde no se requiere autenticación basada en base de datos.</p>
     *
     * @param encoder el codificador de contraseñas usado para cifrar la contraseña del usuario.
     * @return una instancia de {@link InMemoryUserDetailsManager} con el usuario configurado.
     */

    // Creación de un usuario administrador en memoria TEST
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("password"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    /* Bean que define el algoritmo de codificación de contraseñas.
     * BCrypt es seguro, moderno y recomendado para aplicaciones reales.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        //BCrypt es una forma de encriptar
        return new BCryptPasswordEncoder();
    }


    /*
     * Configuración principal de seguridad HTTP.
     * Define CORS, CSRF, manejo de sesiones, reglas de autorización
     * y el mecanismo de autenticación (HTTP Basic).
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF se deshabilita porque la API es stateless y se consumirá desde un cliente separado
        http.csrf(csrf ->
                        csrf.disable()).cors(cors ->
                        cors.configurationSource(request -> {
                    var config = new org.springframework.web.cors.CorsConfiguration();
                    config.setAllowCredentials(true); //PERMITE ENVvio de credenciales
                    config.addAllowedOrigin("http://127.0.0.1:4200/"); //Permite llegada desde FRONTEND (ANGULAR)
                    config.addAllowedHeader("*"); //Permite headers
                    config.addAllowedMethod("*"); //Permite todos los me´todos
                    return config;
                }))
                //Peticiones se deben autenticar por sí misma.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated()
                )
                //Aca configuramos que se aplique HTTp basic
                .httpBasic(Customizer.withDefaults());

        //Retorna Filtro Chain construido y configurado
        return http.build();
    }
}
