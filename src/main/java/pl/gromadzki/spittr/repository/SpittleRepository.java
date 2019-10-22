package pl.gromadzki.spittr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gromadzki.spittr.entity.Spittle;

import java.util.List;

@Repository
public interface SpittleRepository extends CrudRepository<Spittle, Long> {
    List<Spittle> findSpittlesById(int count);
}
