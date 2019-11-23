package pl.gromadzki.spittr.service;

import org.springframework.stereotype.Service;
import pl.gromadzki.spittr.model.Spittle;
import pl.gromadzki.spittr.repository.SpittleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpittlesService {
    private SpittleRepository spittleRepository;

    public SpittlesService(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    public List<Spittle> getLastSpittles(int counter){
        List<Spittle> spittleList = new ArrayList<>();
        int lastId = Math.toIntExact(spittleRepository.count());
        while(counter>=0 && spittleRepository.existsById(lastId)){
            spittleList.add(spittleRepository.findById(lastId));
            lastId--;
            counter--;
        }
        return spittleList;
    }

    public Spittle getRandomSpittle(){
        Integer randomId = Math.toIntExact((long) ((Math.random() * spittleRepository.count()) + 1));
        return spittleRepository.findById(randomId).orElse(null);
    }

    public boolean deleteSpittle(int id){
        if(id > spittleRepository.count() || id < 0){
            return false;
        }
        Spittle spittle = spittleRepository.findById(id);
        if(spittle != null){
            spittleRepository.delete(spittle);
            return true;
        }
        return false;
    }
}
