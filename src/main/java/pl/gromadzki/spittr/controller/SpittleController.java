package pl.gromadzki.spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.gromadzki.spittr.service.SpittlesService;

@Controller
@RequestMapping("/spittle")
public class SpittleController {
    private SpittlesService spittlesService;

    @Autowired
    public SpittleController(SpittlesService spittlesService) {
        this.spittlesService = spittlesService;
}
    @GetMapping
    public String spittles(Model model){
        model.addAttribute("spittles", spittlesService.getLastSpittles(5));
        return "spittle";
    }
}
