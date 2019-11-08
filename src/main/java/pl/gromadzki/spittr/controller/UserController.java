package pl.gromadzki.spittr.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.gromadzki.spittr.model.User;
import pl.gromadzki.spittr.repository.UserRepository;

@Controller
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String getRegistration(Model model){
        model.addAttribute("newUser", new User());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("newUser")User user){
        String password = user.getPassword();
        user.setRole("USER");
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        userRepository.save(user);
        return "redirect:/";
    }
}
