package manager.DataAccessObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDao {
    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
            new User(
                    "princewill016@gmail.com",
                    "Peter2011",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
            new User(
                    "gift@gmail.com",
                    "Peter2011",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))));

    public UserDetails findUserByUsername(String username) {
        return APPLICATION_USERS.stream().filter(u -> u.getUsername().equals(username)).findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("user was not found"));
    }
}