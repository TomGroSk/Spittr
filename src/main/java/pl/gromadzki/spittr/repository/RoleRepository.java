package pl.gromadzki.spittr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gromadzki.spittr.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
