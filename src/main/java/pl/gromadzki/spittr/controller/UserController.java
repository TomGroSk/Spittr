package pl.gromadzki.spittr.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.gromadzki.spittr.model.User;
import pl.gromadzki.spittr.repository.UserRepository;
import pl.gromadzki.spittr.validator.UserValidator;

import javax.validation.Valid;

@Controller
public class UserController {
    private UserValidator userValidator;
    private UserRepository userRepository;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    public UserController(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    @GetMapping("/register")
    public String getRegistration(Model model){
        model.addAttribute("newUser", new User());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute("newUser")User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "register";
        }
        String password = user.getPassword();
        user.setRole("USER");
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        userRepository.save(user);
        return "redirect:/";
    }

    //TODO do adminpanel
    @ResponseBody
    @GetMapping("/update")
    public String getUpdate(@RequestParam String username, @RequestParam String auth){
        if(username.equals("admin")) return "You cannot change admin authorities";
        User user = userRepository.findByUsername(username);
        if(user==null) return "There is no user with this name";
        user.setRole(auth);
        userRepository.save(user);
        return "Success";
    }
}
