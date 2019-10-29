package pl.gromadzki.spittr.service;

import org.springframework.stereotype.Service;
import pl.gromadzki.spittr.entity.Login;
import pl.gromadzki.spittr.entity.User;
import pl.gromadzki.spittr.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User validateUser(Login login) {
        return userRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword());
    }
}
