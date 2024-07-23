package manager.Controllers;

import manager.DataAccessObject.ContactDao;
import manager.DataTransferObject.AuthenticationRequestDTO;
import manager.DataTransferObject.AuthenticationResponseDTO;
import manager.SecurityConfig.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> createAuthenticationToken(

            @RequestBody AuthenticationRequestDTO authenticationRequest) throws Exception {
        try {
            logger.info("trying to authenticate user");
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));
            logger.info("user authenticated successfully");
        } catch (BadCredentialsException ignored) {
            logger.info("cannot authenticate user");
        }
        final UserDetails user = contactDao.findUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtils.generateToken(user);

        return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));
    }
}