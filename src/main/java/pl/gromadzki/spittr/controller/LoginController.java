package pl.gromadzki.spittr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.gromadzki.spittr.entity.Login;
import pl.gromadzki.spittr.entity.User;
import pl.gromadzki.spittr.repository.UserRepository;
import pl.gromadzki.spittr.service.UserService;

@Controller
public class LoginController {
    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showLogin(Model model){
        model.addAttribute("login",new Login());
        return "login";
    }

    @PostMapping
    public String processLogin(@ModelAttribute("login")Login login){
        User user = userService.validateUser(login);

        if(user != null){
            //success
        }
        else{
            //failed
        }


        return "redirect:/spittle";
    }
}
