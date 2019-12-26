package pl.gromadzki.spittr.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.gromadzki.spittr.model.Spittle;
import pl.gromadzki.spittr.repository.SpittleRepository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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
        Integer randomId;
        Spittle spittle = null;
        while (spittle==null){
            randomId = Math.toIntExact((long) ((Math.random() * spittleRepository.count()) + 1));
            spittle = spittleRepository.findById(randomId).orElse(null);
        }
        return spittle;
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
