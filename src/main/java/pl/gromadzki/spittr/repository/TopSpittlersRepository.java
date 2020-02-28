package pl.gromadzki.spittr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gromadzki.spittr.model.TopSpittlers;

import java.util.List;

@Repository
public interface TopSpittlersRepository extends JpaRepository<TopSpittlers, Integer> {
    TopSpittlers findByName(String name);
    List<TopSpittlers> findTop10ByOrderByValueDesc();
}
