package pl.gromadzki.spittr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gromadzki.spittr.entity.Login;
import pl.gromadzki.spittr.entity.User;

@Repository
public interface UserRepository extends CrudRepository <User, Long> {
    User findByUsernameAndPassword(String username, String password);
}
