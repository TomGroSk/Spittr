package pl.gromadzki.spittr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gromadzki.spittr.entity.Spittle;
import pl.gromadzki.spittr.repository.SpittleRepository;

@RestController
@RequestMapping(value = "/api")
public class SpittlesRestController {

    private SpittleRepository spittleRepository;
    public SpittlesRestController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    @GetMapping
    public Iterable<Spittle> getAllSpittles(){
        return spittleRepository.findAll();
    }
}
