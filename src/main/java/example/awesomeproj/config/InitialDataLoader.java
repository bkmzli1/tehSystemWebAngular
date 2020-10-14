package example.awesomeproj.config;


import example.awesomeproj.areas.role.entities.Role;
import example.awesomeproj.areas.role.models.service.RoleServiceModel;
import example.awesomeproj.areas.role.services.RoleService;
import example.awesomeproj.areas.user.models.service.UserServiceModel;
import example.awesomeproj.areas.user.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
@CrossOrigin(origins = "http://localhost:4200")
public class InitialDataLoader implements ApplicationRunner {

    private final RoleService roleService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public InitialDataLoader(RoleService roleService, UserService userService, ModelMapper modelMapper) {
        this.roleService = roleService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public void run(ApplicationArguments args) {
        RoleServiceModel userRole = this.roleService.findByAuthority("USER");
        RoleServiceModel executorRole = this.roleService.findByAuthority("executor".toUpperCase());
        RoleServiceModel adminRole = this.roleService.findByAuthority("ADMIN");

        UserServiceModel userRoot = this.userService.findByUsername("root");
        UserServiceModel userExecutor = this.userService.findByUsername("Все исполнители");

        if (userRole == null) {
            RoleServiceModel roleServiceModel = new RoleServiceModel();
            roleServiceModel.setAuthority("USER");
            this.roleService.addRole(roleServiceModel);
        }
        if (executorRole == null) {
            RoleServiceModel roleServiceModel = new RoleServiceModel();
            roleServiceModel.setAuthority("executor".toUpperCase());
            this.roleService.addRole(roleServiceModel);

        }

        if (adminRole == null) {
            RoleServiceModel roleServiceModel = new RoleServiceModel();
            roleServiceModel.setAuthority("ADMIN");
            this.roleService.addRole(roleServiceModel);
        }

        if (userRoot == null){
            UserServiceModel user = new UserServiceModel();
            user.setPassword("root");
            user.setEmail("-");
            user.setUsername("root");
            user.setAdmin(true);
            this.userService.create(user);
        }
        if (userExecutor == null){
            UserServiceModel user = new UserServiceModel();
            user.setPassword("dqwfdwsfdwfws");
            user.setEmail("-");
            user.setUsername("Все исполнители");
            user.setExecutor(true);
            this.userService.create(user);
        }
    }
}