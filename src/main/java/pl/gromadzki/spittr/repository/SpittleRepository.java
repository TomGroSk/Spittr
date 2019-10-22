package pl.gromadzki.spittr.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.gromadzki.spittr.entity.Spittle;

import java.util.List;

@Repository
public interface SpittleRepository extends CrudRepository<Spittle, Long> {
    //TODO find few last entities
    @Query(value = "SELECT * FROM (SELECT * FROM SPITTLE ORDER BY SPITTLE_ID DESC LIMIT COUNT) sub ORDER BY SPITTLE_ID ASC" , nativeQuery = true)
    List<Spittle> findLastSpittles(@Param("COUNT")int count);
}
