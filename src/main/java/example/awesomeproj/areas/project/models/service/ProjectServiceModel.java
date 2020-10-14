package example.awesomeproj.areas.project.models.service;

import example.awesomeproj.areas.user.entities.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProjectServiceModel {
    private String id;
    private User userCrate;
    private User userExecutor;
    private String text;
    private BigDecimal price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUserCrate() {
        return userCrate;
    }

    public void setUserCrate(User userCrate) {
        this.userCrate = userCrate;
    }

    public User getUserExecutor() {
        return userExecutor;
    }

    public void setUserExecutor(User userExecutor) {
        this.userExecutor = userExecutor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
