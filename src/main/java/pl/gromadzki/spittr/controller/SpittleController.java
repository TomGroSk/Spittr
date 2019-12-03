package pl.gromadzki.spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gromadzki.spittr.model.Spittle;
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
        model.addAttribute("spittles", spittlesService.getLastSpittles());
        return "spittle";
    }

    @PostMapping
    public String messageSpittle(@ModelAttribute("spittle")Spittle spittle, Authentication authentication){
        spittle.setUsername(authentication.getName());
        spittleRepository.save(spittle);
        return "redirect:/spittle";
    }

    @ResponseBody
    @GetMapping(value = "/api")
    public Iterable<Spittle> getAllSpittles(){
        return spittleRepository.findAll();
    }

    @GetMapping(value = "/random")
    public String getRandomSpittle(Model model){
        model.addAttribute("randomSpittle", spittlesService.getRandomSpittle());
        return "random";
    }

    @ResponseBody
    @DeleteMapping(value = "/delete/{id}")
    public String deleteSpittle(@PathVariable("id") int spittleId){
        if(spittlesService.deleteSpittle(spittleId)){
            return spittleId + " is no longer exist!";
        } else {
            return "Failed to delete spittle with ID: " + spittleId;
        }
    }
}
