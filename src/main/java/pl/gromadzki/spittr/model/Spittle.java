package pl.gromadzki.spittr.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "SPITTLES")
public class Spittle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SPITTLE_ID")
    private Integer id;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "TIME")
    private String time;

    @Column(name = "USER_NAME")
    private String username;

    @Override
    public String toString() {
        return "Spittle{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", time=" + time +
                '}';
    }

    public Spittle(String message, String time, String username) {
        this.username = username;
        this.id = null;
        this.message = message;
        this.time = time;
    }
    public Spittle(String message){
        this(message, new SimpleDateFormat("dd-MM-yy HH:mm:ss").format(new Date()), "username");
    }
    public Spittle(){
        this("", new SimpleDateFormat("dd-MM-yy HH:mm:ss").format(new Date()), "username");
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
    public void setMessage(String message){
        this.message = message;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
