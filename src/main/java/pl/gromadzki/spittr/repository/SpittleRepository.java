package pl.gromadzki.spittr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gromadzki.spittr.model.Spittle;

@Repository
public interface SpittleRepository extends JpaRepository<Spittle, Integer> {
    Spittle findById(int count); //default implemented method
}
