package pl.gromadzki.spittr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.gromadzki.spittr.service.TopSpittlersService;

@Controller
@RequestMapping("/spittlers")
public class TopSpittlersController {

    private final TopSpittlersService topSpittlersService;

    public TopSpittlersController(TopSpittlersService topSpittlersService) {
        this.topSpittlersService = topSpittlersService;
    }

    @GetMapping
    public String topList(Model model) {
        model.addAttribute("topSpittlers", topSpittlersService.getTenTopSpittlers());
        return "topSpittlers";
    }

}
