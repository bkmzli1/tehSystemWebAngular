package example.awesomeproj.areas.user.repositories;


import example.awesomeproj.areas.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Set<User> findAllBy();

    User findOneByUsername(String username);

    User findByEmail(String email);

    User findOneById(String id);
}