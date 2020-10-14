package example.awesomeproj.areas.role.services;


import example.awesomeproj.areas.role.entities.Role;
import example.awesomeproj.areas.role.models.service.RoleServiceModel;
import example.awesomeproj.areas.role.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleServiceModel findByAuthority(String authority) {
        Role role = this.roleRepository.findByAuthority(authority);
        RoleServiceModel roleModel = null;
        if (role != null){
            roleModel = this.modelMapper.map(role, RoleServiceModel.class);
        }
        return roleModel;
    }

    @Override
    public void addRole(RoleServiceModel roleServiceModel) {
        Role role = this.modelMapper.map(roleServiceModel, Role.class);
        this.roleRepository.save(role);
    }
}
