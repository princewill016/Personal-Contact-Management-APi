package manager.Controllers;

import manager.DataAccessObject.ContactDao;
import manager.DataTransferObject.AuthenticationRequestDTO;
import manager.DataTransferObject.AuthenticationResponseDTO;
import manager.SecurityConfig.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequiredArgsConstructor

public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ContactDao contactDao;
    @Autowired
    private JwtUtil jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> createAuthenticationToken(
            @RequestBody AuthenticationRequestDTO authenticationRequest) throws Exception {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));

        } catch (BadCredentialsException e) {

        }
        final UserDetails user = contactDao.findUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtils.generateToken(user);

        return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));
    }
}