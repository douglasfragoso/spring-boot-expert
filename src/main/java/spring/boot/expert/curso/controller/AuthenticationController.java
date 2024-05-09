package spring.boot.expert.curso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import spring.boot.expert.curso.dto.AuthenticationDTO;
import spring.boot.expert.curso.dto.ClientDTO;
import spring.boot.expert.curso.model.Client;

import spring.boot.expert.curso.service.TokenService;


@RestController
@RequestMapping(value = "/auth", produces = "application/json")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/v1/login")
    ResponseEntity<ClientDTO> clientLogin(@Valid @RequestBody AuthenticationDTO authenticationDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationDTO.getEmail(),
                            authenticationDTO.getPassword()));

            Client loggedUser = (Client) authentication.getPrincipal();

            String token = tokenService.generateToken(loggedUser);

            ClientDTO clientDTO = new ClientDTO(loggedUser.getId(), loggedUser.getName(), loggedUser.getProfile(), token);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(clientDTO);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password");
        }
    }
    
}
