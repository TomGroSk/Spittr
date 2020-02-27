package pl.gromadzki.spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gromadzki.spittr.model.Spittle;
import pl.gromadzki.spittr.repository.SpittleRepository;
import pl.gromadzki.spittr.service.SpittlesService;
import pl.gromadzki.spittr.service.TopSpittlersService;


@Controller
@RequestMapping("/spittle")
public class SpittleController {
    private SpittlesService spittlesService;
    private SpittleRepository spittleRepository;
    private TopSpittlersService topSpittlersService;

    @Autowired
    public SpittleController(SpittlesService spittlesService, SpittleRepository spittleRepository
            , TopSpittlersService topSpittlersService) {
        this.spittlesService = spittlesService;
        this.spittleRepository = spittleRepository;
        this.topSpittlersService = topSpittlersService;
    }
    @GetMapping
    public String spittles(Model model){
        model.addAttribute("spittle", new Spittle());
        model.addAttribute("spittles", spittlesService.getLastSpittles());
        return "spittle";
    }

    @PostMapping
    public String messageSpittle(@ModelAttribute("spittle")Spittle spittle, Authentication authentication){
        spittle.setUsername(authentication.getName());
        spittleRepository.save(spittle);
        topSpittlersService.increaseCountListOfSpittlers(spittle);
        return "redirect:/spittle";
    }

    @GetMapping(value = "/random")
    public String getRandomSpittle(Model model){
        model.addAttribute("randomSpittle", spittlesService.getRandomSpittle());
        return "random";
    }
}
