package springbootauthorize.repository;

import org.springframework.stereotype.Repository;
import springbootauthorize.permission.Authorities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepository {
    public List<Authorities> getUserAuthorities(String user, String password) {
        List<Authorities> authorities = new ArrayList<>();

        if (user.equals("admin") && password.equals("admin")) {
            Collections.addAll(authorities, Authorities.READ, Authorities.WRITE, Authorities.DELETE);
        } else if (user.equals("manager") && password.equals("0000")) {
            Collections.addAll(authorities, Authorities.READ, Authorities.WRITE);
        } else if (user.equals("user") && password.equals("1234")) {
            Collections.addAll(authorities, Authorities.READ);
        }
        return authorities;
    }
}