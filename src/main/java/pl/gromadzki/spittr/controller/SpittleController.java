package pl.gromadzki.spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.gromadzki.spittr.entity.Spittle;
import pl.gromadzki.spittr.repository.SpittleRepository;
import pl.gromadzki.spittr.service.SpittlesService;


@Controller
@RequestMapping("/spittle")
public class SpittleController {
    private SpittlesService spittlesService;
    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittlesService spittlesService, SpittleRepository spittleRepository) {
        this.spittlesService = spittlesService;
        this.spittleRepository = spittleRepository;
}
    @GetMapping
    public String spittles(Model model){
        model.addAttribute("spittle", new Spittle());
        model.addAttribute("spittles", spittlesService.getLastSpittles(3));
        return "spittle";
    }


    @PostMapping
    public String messageSpittle(@ModelAttribute("spittle")Spittle spittle){
        spittleRepository.save(spittle);
        return "redirect:/spittle";
    }
}
