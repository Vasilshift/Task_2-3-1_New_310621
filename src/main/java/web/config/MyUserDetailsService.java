package web.config;

import jakarta.annotation.PostConstruct;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import web.model.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private Map<String, User> roles = new HashMap<>();

    @PostConstruct
    public void init() {
        roles.put("admin2", new User("admin", "1", getAuthority("ROLE_ADMIN")));
        roles.put("user2", new User("user", "2", getAuthority("ROLE_USER")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return roles.get(username);
    }

    private List<GrantedAuthority> getAuthority(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}