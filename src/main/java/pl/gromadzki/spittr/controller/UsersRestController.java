package pl.gromadzki.spittr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gromadzki.spittr.model.User;
import pl.gromadzki.spittr.repository.UserRepository;

import java.util.List;

@RestController
public class UsersRestController {
    private final UserRepository userRepository;

    public UsersRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
