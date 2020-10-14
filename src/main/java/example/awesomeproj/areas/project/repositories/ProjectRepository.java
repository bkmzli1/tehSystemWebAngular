package example.awesomeproj.areas.project.repositories;

import example.awesomeproj.areas.project.entities.Project;
import example.awesomeproj.areas.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, String> {
    List<Project> findAllByUserCrate(User userCrate);
    List<Project> findAllByUserExecutor(User userExecutor);
    Project findAllById(String id);
}
