package springbootauthorize.repository;

import org.springframework.stereotype.Repository;
import springbootauthorize.permission.Authorities;
import springbootauthorize.permission.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepository {
    List<User> users = Arrays.asList(
            new User("admin", "admin"),
            new User("manager", "0000"),
            new User("user", "1234"));


    public List<Authorities> getUserAuthorities(String name, String password) {
        List<Authorities> authorities = new ArrayList<>();
        for (User user : users) {
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                if (user.getName().equals("admin") && user.getPassword().equals("admin")) {
                    Collections.addAll(authorities, Authorities.READ, Authorities.WRITE, Authorities.DELETE);
                } else if (user.getName().equals("manager") && user.getPassword().equals("0000")) {
                    Collections.addAll(authorities, Authorities.READ, Authorities.WRITE);
                } else if (user.getName().equals("user") && user.getPassword().equals("1234")) {
                    Collections.addAll(authorities, Authorities.READ);
                }
            }
        }
        return authorities;
    }
}