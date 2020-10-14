package example.awesomeproj.areas.user.services;


import example.awesomeproj.areas.role.entities.Role;
import example.awesomeproj.areas.role.models.service.RoleServiceModel;
import example.awesomeproj.areas.role.services.RoleService;
import example.awesomeproj.areas.user.entities.User;
import example.awesomeproj.areas.user.models.service.UserServiceModel;
import example.awesomeproj.areas.user.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Wrong");
        }

        return user;
    }

    @Override
    public void create(UserServiceModel userServiceModel) {
        User userEntity = this.modelMapper.map(userServiceModel, User.class);
        userEntity.setPassword(this.bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.setEnabled(true);
        userEntity.setImg("public/vk.png");

        Set<Role> authorities = new HashSet<>();
        RoleServiceModel roleServiceModel = null;

        roleServiceModel = this.roleService.findByAuthority("USER");
        Role role = this.modelMapper.map(roleServiceModel, Role.class);
        authorities.add(role);

        if (userServiceModel.isAdmin()) {
            roleServiceModel = this.roleService.findByAuthority("ADMIN");
            Role roleAdmin = this.modelMapper.map(roleServiceModel, Role.class);
            authorities.add(roleAdmin);
        }
        if (userServiceModel.isExecutor()) {
            roleServiceModel = this.roleService.findByAuthority("executor".toUpperCase());
            Role roleExecutor = this.modelMapper.map(roleServiceModel, Role.class);
            authorities.add(roleExecutor);
        }


        userEntity.setAuthorities(authorities);
        this.userRepository.save(userEntity);
    }

    @Override
    public void edit(User userServiceModele) {

    }

    @Override
    public boolean isUsernameTaken(String username) {
        return this.userRepository.findOneByUsername(username) != null;
    }

    @Override
    public boolean isEmailTaken(String email) {
        return this.userRepository.findByEmail(email) != null;
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        User userEntity = this.userRepository.findOneByUsername(username);
        UserServiceModel userModel = null;
        if (userEntity != null) {
            userModel = this.modelMapper.map(userEntity, UserServiceModel.class);
        }
        return userModel;
    }

    @Override
    public UserServiceModel findById(String id) {
        User userEntity = this.userRepository.findOneById(id);
        return this.modelMapper.map(userEntity, UserServiceModel.class);
    }


}