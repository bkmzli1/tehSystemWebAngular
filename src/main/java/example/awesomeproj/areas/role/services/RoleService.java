package example.awesomeproj.areas.role.services;


import example.awesomeproj.areas.role.models.service.RoleServiceModel;

public interface RoleService {

    RoleServiceModel findByAuthority(String authority);

    void addRole(RoleServiceModel roleServiceModel);
}
