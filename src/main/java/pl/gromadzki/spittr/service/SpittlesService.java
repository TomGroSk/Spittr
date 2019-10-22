package pl.gromadzki.spittr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gromadzki.spittr.entity.Spittle;
import pl.gromadzki.spittr.repository.SpittleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpittlesService {
    private SpittleRepository spittleRepository;

    @Autowired
    public SpittlesService(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    public List<Spittle> getLastSpittles(int counter){
        List<Spittle> spittleList = new ArrayList<>();
        List<Long> longList = new ArrayList<>();
        longList.add(Long.MAX_VALUE);








        return spittleList;
    }
}
