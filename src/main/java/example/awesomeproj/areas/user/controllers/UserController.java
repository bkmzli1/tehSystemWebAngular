package example.awesomeproj.areas.user.controllers;


import example.awesomeproj.areas.role.entities.Role;

import example.awesomeproj.areas.user.entities.User;
import example.awesomeproj.areas.user.models.binding.UserRegisterBindingModel;
import example.awesomeproj.areas.user.models.service.UserServiceModel;
import example.awesomeproj.areas.user.repositories.UserRepository;
import example.awesomeproj.areas.user.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.Serializable;
import java.security.Principal;
import java.util.*;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserService userService;


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @PostMapping(value = "/reg")
    @ResponseBody
    public Map<String, String> registerConfirm(@RequestBody() @Valid UserRegisterBindingModel userRegisterBindingModel,
                                               BindingResult bindingResult,
                                               HttpServletRequest request) {

        Map<String, String> strings = new HashMap<>();
        if (bindingResult.hasErrors()) {
            String errorS = "";
            int size = 0;
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorS += error.getDefaultMessage();
                size++;
                if (size < bindingResult.getAllErrors().size()) {
                    errorS += ",";
                } else {
                    errorS += ".";
                }
            }
            strings.put("error", errorS);
            return strings;
        } else {
            UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
            this.userService.create(userServiceModel);
            strings.put("error", null);


        }
        return strings;
    }


    @RequestMapping("/user")
    public Map<String, Object> user(Principal user) {
        try {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("id", UUID.randomUUID().toString());
            map.put("name", user.getName());
            map.put("roles", AuthorityUtils.authorityListToSet(((Authentication) user).getAuthorities()));
            UserServiceModel user1 = this.userService.findByUsername(user.getName());
            user1.setPassword(null);
            map.put("user", user1);
            return map;
        } catch (NullPointerException npe) {

        }
        return null;

    }

    @RequestMapping("/executor")
    public Set<Object> executor() {
        Set<Object> users = new HashSet<>();
        Stream<User> stream = userRepository.findAllBy().stream();
        stream.filter(x  -> x.roleStr().toString().contains("executor".toUpperCase())).forEach(user -> {
            Map<String, Object> mapUser = new LinkedHashMap<>();
            mapUser.put("id", user.getId());
            mapUser.put("name", user.getUsername());
            mapUser.put("email", user.getEmail());
            mapUser.put("img", user.getImg());

            Set<String> roleStr = new HashSet<>();
            for (Role role : user.getAuthorities()) {
                roleStr.add(role.getAuthority());
            }

            mapUser.put("auth", roleStr);
            users.add(mapUser);
        });


        return users;
    }

    @RequestMapping("/")
    @CrossOrigin(origins = "*", maxAge = 3600,
            allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
    public Message home() {
        return new Message("Hello World");
    }


    @RequestMapping("/id/{id}")
    public  Map<String, Object> img(@PathVariable String id) {
        User user = null;

        try {
            user =  userRepository.findOneById(id);
        }catch (NullPointerException e){

        }
        Map<String, Object> mapUser = new LinkedHashMap<>();
        mapUser.put("id", user.getId());
        mapUser.put("name", user.getUsername());
        mapUser.put("email", user.getEmail());
        mapUser.put("img", user.getImg());
        return mapUser;
    }
    class Message implements Serializable {
        private final String id = "123";
        private String content;

        public Message(String content) {
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    @PutMapping("/edit")
    public UserServiceModel edit(@RequestBody User userEd) {
        UserServiceModel user = this.modelMapper.map( userRepository.findOneById(userEd.getId()), UserServiceModel.class);
        user.setLastName(userEd.getLastName());
        user.setFirstName(userEd.getFirstName());
        user.setMiddleName(userEd.getMiddleName());
        user.setPassword(userEd.getPassword());
        user.setEmail(userEd.getEmail());
        userRepository.findOneById(userEd.getId()).getAuthorities().stream().forEach(role -> {
            if (role.getAuthority().equals("ADMIN")) user.setAdmin(true);
            if (role.getAuthority().equals("EXECUTOR")) user.setExecutor(true);
        });
        this.userService.create(user);

        return user;
    }
}