package pl.gromadzki.spittr.service;

import org.springframework.stereotype.Service;
import pl.gromadzki.spittr.model.Spittle;
import pl.gromadzki.spittr.repository.SpittleRepository;

import java.util.List;
import java.util.Random;

@Service
public class SpittlesService {
    private SpittleRepository spittleRepository;

    public SpittlesService(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    public List<Spittle> getLastSpittles(){
        return spittleRepository.findFirst3ByOrderByIdDesc();
    }

    public Spittle getRandomSpittle(){
        List<Spittle> spittleList = spittleRepository.findAll();
        Random rand = new Random();
        return spittleList.get(rand.nextInt(spittleList.size()));
    }

    public boolean deleteSpittle(int id){
        Spittle spittle = spittleRepository.findById(id);
        if(spittle != null){
            spittleRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
