package example.awesomeproj.areas.project.controllers;

import example.awesomeproj.areas.project.entities.Project;
import example.awesomeproj.areas.project.models.binding.CreateProjectBindingModel;
import example.awesomeproj.areas.project.repositories.ProjectRepository;
import example.awesomeproj.areas.user.entities.User;
import example.awesomeproj.areas.user.repositories.UserRepository;
import example.awesomeproj.areas.user.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/project")
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public ProjectController(ProjectRepository projectRepository, ModelMapper modelMapper, UserService userService, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public String crate(@RequestBody @Valid CreateProjectBindingModel bindingModel) {
        try {
            Project project = new Project();
            project.setUserCrate(userRepository.findOneById(bindingModel.getUserCrate()));
            project.setUserExecutor(userRepository.findOneById(bindingModel.getUserExecutor()));
            project.setText(bindingModel.getText());
            project.setDone(false);
            projectRepository.save(project);
        } catch (Exception e) {
            return "error";
        }

        return "ok";
    }

    @PostMapping(value = "/get")
    @ResponseBody
    public HashSet<Project> getProject(@RequestBody @Valid String id) {
        AtomicBoolean root = new AtomicBoolean(false);
        HashSet<Project> projects = new HashSet<>();
        List<Project> projectRepositoryAll = projectRepository.findAll();
        Stream<Project> stream = projectRepositoryAll.stream();
        userRepository.findOneById(id).getAuthorities().stream().filter(x -> x.getAuthority().equals("ADMIN")).forEach(auth ->{
            projects.addAll(projectRepositoryAll);
            root.set(true);
        });
        if (!root.get())
        stream.filter(x->
                x.getUserCrate().getId().equals(id) ||
                x.getUserExecutor().getId().equals(id) ||
                ((!isRoleUser(x.getUserCrate(), "USER")) & x.getUserExecutor().getUsername().equals("Все исполнители"))
        ).forEach(projects::add);


        return projects;
    }

    private boolean isRoleUser(User user,String role){
        AtomicBoolean isRole = new AtomicBoolean(false);
        user.getAuthorities().stream().filter(x -> x.getAuthority().equals(role)).forEach(x -> isRole.set(true) );
        return isRole.get();
    }
}
