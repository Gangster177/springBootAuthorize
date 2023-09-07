package springbootauthorize.service;

import org.springframework.validation.annotation.Validated;
import springbootauthorize.exception.InvalidCredentials;
import springbootauthorize.exception.UnauthorizedUser;
import org.springframework.stereotype.Service;
import springbootauthorize.permission.Authorities;
import springbootauthorize.permission.User;
import springbootauthorize.repository.UserRepository;

import java.util.List;

@Service
public class AuthorizationService {
    UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(User user) {
        if (isEmpty(user.getName()) || isEmpty(user.getPassword())) {
            throw new InvalidCredentials("user or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user.getName(),
                user.getPassword());
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("unknown user " + user.getName());
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}