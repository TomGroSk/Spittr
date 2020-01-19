package pl.gromadzki.spittr.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Entity
@Table(name = "SPITTLES")
public class Spittle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPITTLE_GEN")
    @SequenceGenerator(name="SPITTLE_GEN")
    @Column(name = "SPITTLE_ID")
    private Integer id;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "TIME")
    private String time;

    @Column(name = "USER_NAME")
    private String username;

    @Lob
    @Column(name = "IMAGE")
    private String base64Image;

    @Transient
    private MultipartFile multipartFile;

    @Override
    public String toString() {
        return "Spittle{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", time=" + time +
                '}';
    }

    //constructors
    public Spittle(){
        this("", new SimpleDateFormat("dd-MM-yy HH:mm:ss").format(new Date()), "username");
    }
    public Spittle(String message){
        this(message, new SimpleDateFormat("dd-MM-yy HH:mm:ss").format(new Date()), "username");
    }
    public Spittle(String message, String time, String username) {
        this.username = username;
        this.id = null;
        this.message = message;
        this.time = time;
        this.multipartFile = null;
        this.base64Image = "";
    }
    public Spittle(String message, String time, String username, MultipartFile multipartFile) {
        this.username = username;
        this.id = null;
        this.message = message;
        this.time = time;
        this.multipartFile = multipartFile;
        this.base64Image = "";
    }

    //getters and setters
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
    public MultipartFile getMultipartFile() {
        return multipartFile;
    }
    public void setMultipartFile(MultipartFile file) {
        this.multipartFile = file;
        try {
            byte[] encodeBase64 = Base64.getEncoder().encode(file.getBytes()); //changing byte array of image to base64
            String base64Encoded = new String(encodeBase64, StandardCharsets.UTF_8);
            setBase64Image(base64Encoded);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getBase64Image() {
        return base64Image;
    }
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
