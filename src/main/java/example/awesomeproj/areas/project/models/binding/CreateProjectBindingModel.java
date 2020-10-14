package example.awesomeproj.areas.project.models.binding;

import example.awesomeproj.areas.user.entities.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateProjectBindingModel {

    @NotEmpty
    private String userCrate;
    @NotEmpty
    private String userExecutor;
    @NotNull
    private String text;


    public CreateProjectBindingModel() {
    }

    public String getUserCrate() {
        return userCrate;
    }

    public void setUserCrate(String userCrate) {
        this.userCrate = userCrate;
    }

    public String getUserExecutor() {
        return userExecutor;
    }

    public void setUserExecutor(String userExecutor) {
        this.userExecutor = userExecutor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
