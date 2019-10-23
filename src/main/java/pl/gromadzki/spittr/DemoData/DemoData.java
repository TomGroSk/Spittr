package pl.gromadzki.spittr.DemoData;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.gromadzki.spittr.entity.Spittle;
import pl.gromadzki.spittr.repository.SpittleRepository;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class DemoData {
    private final SpittleRepository repository;
    public DemoData(SpittleRepository repository) {
        this.repository = repository;
    }
    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
        repository.save(new Spittle("Testowa wkafsghasjkdfhas jkdhfags jkdfhagsdfjhaskdf jhgsakdjfhagsdkfh asdkfhasgdfjkash iadomosc", "11-09-19 08:20:11"));
        repository.save(new Spittle("Testowa wiadomosc 2", "01-10-19 19:32:11"));
        repository.save(new Spittle("Testowa wiadomosc 3", simpleDateFormat.format(new Date())));
        //repository.findAll().forEach(System.out::println);
    }
}
