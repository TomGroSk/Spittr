package pl.gromadzki.spittr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.gromadzki.spittr.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
