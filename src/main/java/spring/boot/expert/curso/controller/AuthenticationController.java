package spring.boot.expert.curso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import spring.boot.expert.curso.controller.exception.ValidationError;
import spring.boot.expert.curso.dto.AuthenticationDTO;
import spring.boot.expert.curso.dto.ClientLoginDTO;
import spring.boot.expert.curso.model.Client;
import spring.boot.expert.curso.service.TokenService;

@RestController
@RequestMapping(value = "/auth", produces = "application/json")
@Tag(name = "Auth", description = "Authentication API")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/v1/login")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully authenticated", content = @Content(schema = @Schema(implementation = ClientLoginDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = ValidationError.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ValidationError.class)))})
    @Operation(summary = "Client login", description = "Client login", tags = { "Auth" , "POST"})
    ResponseEntity<ClientLoginDTO> clientLogin(@Valid @RequestBody AuthenticationDTO authenticationDTO) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationDTO.getEmail(),
                            authenticationDTO.getPassword()));

            Client loggedUser = (Client) authentication.getPrincipal();

            String token = tokenService.generateToken(loggedUser);

            ClientLoginDTO clientDTO = new ClientLoginDTO(loggedUser.getId(), loggedUser.getName(), token);

            return ResponseEntity.status(HttpStatus.OK).body(clientDTO);
    }
}
