package springbootauthorize.controller;

import org.springframework.validation.annotation.Validated;
import springbootauthorize.exception.InvalidCredentials;
import springbootauthorize.exception.UnauthorizedUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springbootauthorize.permission.Authorities;
import springbootauthorize.permission.User;
import springbootauthorize.service.AuthorizationService;

import java.util.List;

@RestController
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@Validated User user) {
        return service.getAuthorities(user);
    }

    // на InvalidCredentials он должен отсылать обратно клиенту HTTP-статус с кодом 400
    // и телом в виде сообщения из exception;
    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> invalidCredentialsHandler(InvalidCredentials e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // на UnauthorizedUser он должен отсылать обратно клиенту HTTP-статус с кодом 401
    // и телом в виде сообщения из exception и писать в консоль сообщение из exception
    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> unauthorizedUserHandler(UnauthorizedUser e) {
        System.out.println("Exception: " + e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
