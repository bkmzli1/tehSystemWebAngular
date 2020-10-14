package example.awesomeproj.areas.project.services;

import example.awesomeproj.areas.project.models.service.ProjectServiceModel;

import java.util.List;

public interface ProjectService {
    List<ProjectServiceModel> findSalesByUsername(String username);

    void createSale(ProjectServiceModel saleServiceModel);
}
