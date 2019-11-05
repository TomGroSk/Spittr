//package pl.gromadzki.spittr.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import pl.gromadzki.spittr.model.User;
//import pl.gromadzki.spittr.repository.UserRepository;
//
//@Controller
//@RequestMapping(value = "/register")
//public class RegistrationController {
//    private UserRepository userRepository;
//
//    public RegistrationController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @GetMapping
//    public String showRegistration(Model model){
//        model.addAttribute("user", new User());
//        return "register";
//    }
//
//    @PostMapping
//    public String addUser(@ModelAttribute("user") User user){
//        userRepository.save(user);
//        return "redirect:/spittle";
//    }
//}
