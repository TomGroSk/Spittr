package pl.gromadzki.spittr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gromadzki.spittr.model.Spittle;

import java.util.List;

@Repository
public interface SpittleRepository extends JpaRepository<Spittle, Integer> {
    Spittle findById(int count);

    List<Spittle> findFirst3ByOrderByIdDesc();
}
