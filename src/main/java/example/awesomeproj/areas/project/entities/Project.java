package example.awesomeproj.areas.project.entities;

import example.awesomeproj.areas.project.models.binding.CreateProjectBindingModel;
import example.awesomeproj.areas.user.entities.User;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Table(name = "project")
public class Project {

    private String id;
    private User userCrate;
    private User userExecutor;
    private String text;
    private boolean done;

    public Project() {
        this.done =false;
    }



    public Project(String id, User userCrate, User userExecutor, String text) {

    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_crate_id",referencedColumnName = "id")
    public User getUserCrate() {
        return userCrate;
    }

    public void setUserCrate(User userCrate) {
        this.userCrate = userCrate;
    }

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_executor_id",referencedColumnName = "id")
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


    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
