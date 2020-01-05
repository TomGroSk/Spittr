package pl.gromadzki.spittr.model.pojo;

import org.springframework.context.annotation.Bean;
import pl.gromadzki.spittr.model.Spittle;

public class SpittleToJson {
    private Integer id;
    private String messageContent;
    private String time;
    private String username;
    private Boolean haveImage = false;

    public SpittleToJson() {
    }

    public SpittleToJson(Integer id, String messageContent, String time, String username, Boolean haveImage) {
        this.id = id;
        this.messageContent = messageContent;
        this.time = time;
        this.username = username;
        this.haveImage = haveImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
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

    public Boolean getHaveImage() {
        return haveImage;
    }

    public void setHaveImage(Boolean haveImage) {
        this.haveImage = haveImage;
    }

    @Bean
    public SpittleToJson convertSpittleToJson(Spittle spittle){
        setId(spittle.getId());
        setMessageContent(spittle.getMessage());
        setTime(spittle.getTime());
        setUsername(spittle.getUsername());
        if(spittle.getBase64Image()!=null){
            setHaveImage(true);
        }
        return this;
    }
}
