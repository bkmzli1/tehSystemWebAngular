package example.awesomeproj.areas.user.models.service;



import example.awesomeproj.areas.Img.entities.Img;
import example.awesomeproj.areas.role.entities.Role;
import example.awesomeproj.areas.role.models.service.RoleServiceModel;
import example.awesomeproj.areas.user.entities.User;

import java.util.HashSet;
import java.util.Set;

public class UserServiceModel {

    private String id;

    private String password;

    private String username;

    private String email;

    private boolean admin;

    private boolean executor;

    private Img img;
    private String firstName;
    private String lastName;
    private String middleName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Img getImg() {
        return img;
    }

    public void setImg(Img img) {
        this.img = img;
    }
}