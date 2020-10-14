package example.awesomeproj.areas.user.services;


import example.awesomeproj.areas.user.entities.User;
import example.awesomeproj.areas.user.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends UserDetailsService {
    void create(UserServiceModel userServiceModele);
    void edit(User userServiceModele);

    boolean isUsernameTaken(String username);

    boolean isEmailTaken(String email);

    UserServiceModel findByUsername(String username);
    UserServiceModel findById(String id);

}