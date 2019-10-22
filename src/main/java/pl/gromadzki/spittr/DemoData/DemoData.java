package pl.gromadzki.spittr.DemoData;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.gromadzki.spittr.entity.Spittle;
import pl.gromadzki.spittr.repository.SpittleRepository;

import java.util.Date;


@Component
public class DemoData {

    private final SpittleRepository repository;

    public DemoData(SpittleRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        repository.save(new Spittle("Testowa wiadomosc", new Date(),2.1 ,2.4));
        repository.save(new Spittle("Testowa wiadomosc 2", new Date(),2.1 ,2.4));
        repository.save(new Spittle("Testowa wiadomosc 3", new Date(),2.1 ,2.4));
        repository.findAll().forEach(System.out::println);
        System.out.println("asdasda");
    }
}
