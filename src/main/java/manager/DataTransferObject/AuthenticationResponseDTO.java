package manager.DataTransferObject;

import lombok.Getter;

@Getter
public class AuthenticationResponseDTO {
    private final String jwt;

    public AuthenticationResponseDTO(String jwt) {
        this.jwt = jwt;
    }

}