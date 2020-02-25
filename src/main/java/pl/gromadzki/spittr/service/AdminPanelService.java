package pl.gromadzki.spittr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gromadzki.spittr.model.Spittle;
import pl.gromadzki.spittr.model.pojo.SpittleToAPI;
import pl.gromadzki.spittr.repository.SpittleRepository;
import pl.gromadzki.spittr.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminPanelService {
    private final SpittleRepository spittleRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdminPanelService(SpittleRepository spittleRepository, UserRepository userRepository) {
        this.spittleRepository = spittleRepository;
        this.userRepository = userRepository;
    }

    public List<SpittleToAPI> getAllUserSpittles(String username){
        List<Spittle> spittlesList = spittleRepository.findAll();
        List<SpittleToAPI> spittleToAPIS = new ArrayList<>();

        spittlesList.removeIf(s -> !username.equals(s.getUsername()));
        for(Spittle s: spittlesList){
            spittleToAPIS.add(new SpittleToAPI().convertSpittleToJson(s));
        }

        return spittleToAPIS;
    }

    public void deleteSpittlesFromDeletedUser(String username){
        for(Spittle s: spittleRepository.findAll()){
            if(s.getUsername().equals(username)){
                spittleRepository.delete(s);
            }
        }
    }
}
