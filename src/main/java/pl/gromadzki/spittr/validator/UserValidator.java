package pl.gromadzki.spittr.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.gromadzki.spittr.model.User;
import pl.gromadzki.spittr.repository.UserRepository;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors err) {
        ValidationUtils.rejectIfEmpty(err, "username", "user.name.empty");
        ValidationUtils.rejectIfEmpty(err, "password", "user.password.empty");

        User user = (User) obj;
        if (!user.getPassword().equals(user.getRePassword())) { //if passwords are the same
            err.rejectValue("rePassword", "user.rePassword.notEqual");
        }
        for (User u : userRepository.findAll()) { //if username already exist
            if (u.getUsername().equals(user.getUsername())) {
                err.rejectValue("username", "user.username.exist");
            }
        }
    }

}
