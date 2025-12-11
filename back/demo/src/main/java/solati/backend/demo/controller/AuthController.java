package solati.backend.demo.controller;

/*import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import solati.backend.demo.dto.AuthInput;
import solati.backend.demo.dto.AuthOutput;
import solati.backend.demo.security.JwtService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    //private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @PostMapping("/login")
    public ResponseEntity<AuthOutput> login(@RequestBody AuthInput request) {
        String tokeFa = "test-token-" + request.username();
        return ResponseEntity.ok(new AuthOutput(tokeFa));
    }


        /*@PostMapping("/login")
    public ResponseEntity<AuthOutput> login(@RequestBody AuthInput request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new AuthOutput(token));
    }



}
*/
