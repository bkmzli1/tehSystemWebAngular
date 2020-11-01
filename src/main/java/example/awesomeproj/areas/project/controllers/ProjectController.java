package example.awesomeproj.areas.project.controllers;

import example.awesomeproj.areas.Img.entities.Img;
import example.awesomeproj.areas.Img.repositories.ImgRepository;
import example.awesomeproj.areas.project.entities.Project;
import example.awesomeproj.areas.project.models.binding.CreateProjectBindingModel;
import example.awesomeproj.areas.project.repositories.ProjectRepository;
import example.awesomeproj.areas.user.entities.User;
import example.awesomeproj.areas.user.repositories.UserRepository;
import example.awesomeproj.areas.user.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/project")
public class ProjectController {
    @Value("${upload.path}")
    private String uploadPath;

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ImgRepository imgRepository;

    @Autowired
    public ProjectController(ProjectRepository projectRepository, ModelMapper modelMapper, UserService userService, UserRepository userRepository, ImgRepository imgRepository) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userRepository = userRepository;
        this.imgRepository = imgRepository;
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public Map<String, Object> crate(@RequestBody @Valid CreateProjectBindingModel bindingModel) {
        Map<String, Object> log = new HashMap<>();
        try {
            Project project = new Project();
            project.setUserCrate(userRepository.findOneById(bindingModel.getUserCrate()));
            project.setUserExecutor(userRepository.findOneById(bindingModel.getUserExecutor()));
            project.setText(bindingModel.getText());
            project.setDone(false);
            project.setCreationDate(new Date());

            //project.setImg(bindingModel.getImg());
            projectRepository.save(project);
            log.put("id", project.getId());
            return log;
        } catch (Exception e) {
            log.put("error", e);
            return log;
        }


    }

    @PostMapping(value = "/create/{id}")
    @ResponseBody
    public Map<Object, Object> addImg(@RequestBody @Valid MultipartFile[] img, @PathVariable String id) {
        Map<Object, Object> log = new HashMap<>();
        try {
            Project project = projectRepository.findAllById(id);
            Set<Img> imgs = new HashSet<>();
            for (MultipartFile mf : img) {
                if (img != null) {
                    String upFile = "privet/img";
                    File uploadPDir = new File(uploadPath + "/" + upFile);
                    if (!uploadPDir.exists()) {
                        uploadPDir.mkdirs();
                    }
                    String uuidFile = UUID.randomUUID().toString();
                    String resultFilename = uuidFile + "." + mf.getOriginalFilename();

                    try {
                        mf.transferTo(new File(uploadPDir + "/" + resultFilename));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Img imgDB = new Img();
                    imgDB.setName(mf.getResource().getFilename());
                    imgDB.setImg(upFile + "/" + resultFilename);
                    imgRepository.save(imgDB);

                    imgs.add(imgDB);
                }
            }
            project.setImg(imgs);
            projectRepository.save(project);
            log.put(img, id);
            return log;
        } catch (Exception e) {
            log.put("error", e);
            return log;
        }


    }

    @PostMapping(value = "/get")
    @ResponseBody
    public Set<Project> getProjects(@RequestBody @Valid String id) {
        AtomicBoolean root = new AtomicBoolean(false);

        Set<Project> projects = new TreeSet<>(Comparator.comparing(Project::getId).reversed());
        List<Project> projectRepositoryAll = projectRepository.findAll();
        Stream<Project> stream = projectRepositoryAll.stream();
        userRepository.findOneById(id).getAuthorities().stream().filter(x -> x.getAuthority().equals("ADMIN")).forEach(auth -> {
            projects.addAll(projectRepositoryAll);
            root.set(true);
        });
        if (!root.get())
            stream.filter(x ->
                    x.getUserCrate().getId().equals(id) ||
                            x.getUserExecutor().getId().equals(id) ||
                            ((!isRoleUser(x.getUserCrate(), "USER")) & x.getUserExecutor().getUsername().equals("Все исполнители"))
            ).forEach(projects::add);


        return projects;
    }

    @PostMapping(value = "/get/{id}")
    @ResponseBody
    public Project getProject(@PathVariable String id) {
        return projectRepository.findAllById(id);
    }

    private boolean isRoleUser(User user, String role) {
        AtomicBoolean isRole = new AtomicBoolean(false);
        user.getAuthorities().stream().filter(x -> x.getAuthority().equals(role)).forEach(x -> isRole.set(true));
        return isRole.get();
    }
}
