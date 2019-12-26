package pl.gromadzki.spittr.demoData.pojo;

import pl.gromadzki.spittr.model.Spittle;

public class SpittleToJson {
    private Integer id;
    private String message;
    private String time;
    private String username;

    public SpittleToJson() {
    }

    public SpittleToJson(Integer id, String message, String time, String username) {
        this.id = id;
        this.message = message;
        this.time = time;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
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
    public SpittleToJson convertSpittleToJson(Spittle spittle){
        setId(spittle.getId());
        setMessage(spittle.getMessage());
        setTime(spittle.getTime());
        setUsername(spittle.getUsername());
        return this;
    }
}
