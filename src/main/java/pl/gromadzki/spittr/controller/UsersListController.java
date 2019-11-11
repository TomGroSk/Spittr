package pl.gromadzki.spittr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.gromadzki.spittr.repository.UserRepository;


@Controller
public class UsersListController {
    private final UserRepository userRepository;

    public UsersListController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/users")
    public String getUsers(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "api";
    }
}
