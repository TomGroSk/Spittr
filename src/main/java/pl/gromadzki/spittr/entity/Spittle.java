package pl.gromadzki.spittr.entity;

import javax.persistence.*;

@Entity
@Table(name = "SPITTLE")
public class Spittle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SPITTLE_ID")
    private final Integer id;

    @Column(name = "MESSAGE")
    private final String message;

    @Column(name = "TIME")
    private final String time;


    public Spittle(String message, String time) {
        this(message, time, null, null);
    }
    public Spittle(String message, String time, Double longitude, Double latitude) {
        this.id = null;
        this.message = message;
        this.time = time;
    }
    public Spittle(){
        this("",null);
    }

    public Integer getId() {
        return id;
    }
    public String getMessage() {
        return message;
    }
    public String getTime() {
        return time;
    }
    @Override
    public String toString() {
        return "Spittle{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", time=" + time +
                '}';
    }
}
