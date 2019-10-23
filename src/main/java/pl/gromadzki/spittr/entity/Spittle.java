package pl.gromadzki.spittr.entity;

import javax.persistence.*;
import java.util.Date;

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
    private final Date time;

    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;

    public Spittle(String message, Date time) {
        this(message, time, null, null);
    }
    public Spittle(String message, Date time, Double longitude, Double latitude) {
        this.id = null;
        this.message = message;
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public Spittle(){
        this("",null,null,null);
    }

    public Integer getId() {
        return id;
    }
    public String getMessage() {
        return message;
    }
    public Date getTime() {
        return time;
    }
    public Double getLongitude() {
        return longitude;
    }
    public Double getLatitude() {
        return latitude;
    }
    @Override
    public String toString() {
        return "Spittle{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", time=" + time +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
