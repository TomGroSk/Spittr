package pl.gromadzki.spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.gromadzki.spittr.repository.SpittleRepository;

@Controller
@RequestMapping("/spittle")
public class SpittleController {
    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
}
    @GetMapping
    public String spittles(Model model){
        model.addAllAttributes(spittleRepository.findSpittlesById(3));
        return "spittle";
    }
}
