package example.awesomeproj.areas.project.services;

import example.awesomeproj.areas.project.entities.Project;
import example.awesomeproj.areas.project.models.service.ProjectServiceModel;
import example.awesomeproj.areas.project.repositories.ProjectRepository;
import example.awesomeproj.areas.user.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProjectServiceImpl implements ProjectService{
    private final ModelMapper modelMapper;

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectServiceImpl(ModelMapper modelMapper, ProjectRepository projectRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ProjectServiceModel> findSalesByUsername(String username) {
        List<ProjectServiceModel> ProjectServiceModels = new ArrayList<>();

        this.projectRepository.findAllByUserCrate(userRepository.findOneByUsername(username)).forEach(saleEntity -> {
            ProjectServiceModel projectServiceModel = this.modelMapper.map(saleEntity, ProjectServiceModel.class);
            ProjectServiceModels.add(projectServiceModel);
        });

        return ProjectServiceModels;
    }

    @Override
    public void createSale(ProjectServiceModel saleServiceModel) {
        Project saleEntity = this.modelMapper.map(saleServiceModel, Project.class);
        this.projectRepository.save(saleEntity);
    }
}
