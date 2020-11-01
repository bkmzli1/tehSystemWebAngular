package example.awesomeproj.areas.user.models.binding;


import example.awesomeproj.areas.user.models.binding.validations.IsEmailRegistered;
import example.awesomeproj.areas.user.models.binding.validations.IsPasswordMatching;
import example.awesomeproj.areas.user.models.binding.validations.IsUsernameTaken;
import example.awesomeproj.constants.Constants;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@IsPasswordMatching
public class UserRegisterBindingModel {
    @Size(min = 4, max = 20, message = Constants.USERNAME_LENGTH)
    @IsUsernameTaken
    private String username;

    @Size(min = 4, max = 30, message = Constants.PASSWORD_LENGTH)
    private String password;

    private String confirmPassword;

    @NotEmpty(message = Constants.ENTER_VALID_EMAIL)
    @Email(message = Constants.ENTER_VALID_EMAIL)
    @IsEmailRegistered
    private String email;

    private boolean admin;
    private boolean executor;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isExecutor() {
        return executor;
    }

    public void setExecutor(boolean executor) {
        this.executor = executor;
    }
}