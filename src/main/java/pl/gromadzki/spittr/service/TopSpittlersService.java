package pl.gromadzki.spittr.service;

import org.springframework.stereotype.Service;
import pl.gromadzki.spittr.model.Spittle;
import pl.gromadzki.spittr.model.TopSpittlers;
import pl.gromadzki.spittr.repository.SpittleRepository;
import pl.gromadzki.spittr.repository.TopSpittlersRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopSpittlersService {

    private TopSpittlersRepository topSpittlersRepository;
    private SpittleRepository spittleRepository;

    public TopSpittlersService(TopSpittlersRepository topSpittlersRepository, SpittleRepository spittleRepository) {
        this.topSpittlersRepository = topSpittlersRepository;
        this.spittleRepository = spittleRepository;
    }

    public Map<String, Integer> getListCountOfSpittlers(){
        Map<String, Integer> mapOfSpittlers = new HashMap<>();
        List<Spittle> spittleList = spittleRepository.findAll();

        for(Spittle s: spittleList){
            mapOfSpittlers.putIfAbsent(s.getUsername(), 0);
            mapOfSpittlers.computeIfPresent(s.getUsername(), (key, val) -> val + 1);
        }

        return mapOfSpittlers;
    }

    public void increaseCountListOfSpittlers(Spittle spittle){
        TopSpittlers topSpittlers = topSpittlersRepository.findByName(spittle.getUsername());
        if(topSpittlers == null){
            topSpittlersRepository.save(new TopSpittlers(spittle.getUsername(), 1));
        }
        else{
            topSpittlers.setValue((topSpittlers.getValue()+1));
            topSpittlersRepository.save(topSpittlers);
        }
    }

    public void decreaseCountListOfSpittlers(Spittle spittle){
        TopSpittlers topSpittlers = topSpittlersRepository.findByName(spittle.getUsername());
        if(topSpittlers.getValue()-1 <= 0){
            topSpittlersRepository.delete(topSpittlers);
        }
        else{
            topSpittlers.setValue((topSpittlers.getValue()-1));
        }

        topSpittlersRepository.save(topSpittlers);
    }
}
