package example.awesomeproj.areas.Img.repositories;


import example.awesomeproj.areas.Img.entities.Img;
import example.awesomeproj.areas.role.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgRepository extends JpaRepository<Img, String> {

}
