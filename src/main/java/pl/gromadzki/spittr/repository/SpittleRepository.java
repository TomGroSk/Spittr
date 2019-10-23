package pl.gromadzki.spittr.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gromadzki.spittr.entity.Spittle;


@Repository
public interface SpittleRepository extends CrudRepository<Spittle, Integer> {
    Spittle findById(int count);

}
