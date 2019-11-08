package pl.gromadzki.spittr.DemoData;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.gromadzki.spittr.model.Spittle;
import pl.gromadzki.spittr.model.User;
import pl.gromadzki.spittr.repository.SpittleRepository;
import pl.gromadzki.spittr.repository.UserRepository;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;


@Component
public class DemoData {
    private final SpittleRepository spittleRepository;
    private final UserRepository userRepository;
    public DemoData(SpittleRepository spittleRepository, UserRepository userRepository) {
        this.spittleRepository = spittleRepository;
        this.userRepository = userRepository;
    }
    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
        spittleRepository.save(new Spittle("Lorem ipsum 1", "11-09-19 08:20:11", "username"));
        spittleRepository.save(new Spittle("Lorem ipsum 2", "01-10-19 19:32:11", "username"));
        spittleRepository.save(new Spittle("Lorem ipsum 3", simpleDateFormat.format(new Date()), "username"));

        userRepository.save(new User("user", new BCryptPasswordEncoder().encode("user"), "USER"));
        userRepository.save(new User("admin", new BCryptPasswordEncoder().encode("admin"), "ADMIN"));
        //userRepository.findAll().forEach(System.out::println);
    }
}
