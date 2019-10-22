package pl.gromadzki.spittr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class controller {

    @GetMapping
    public String smth(){
        return "ala";
    }
}
