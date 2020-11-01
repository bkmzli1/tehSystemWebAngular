package example.awesomeproj.areas.project.entities;

import example.awesomeproj.areas.Img.entities.Img;
import example.awesomeproj.areas.project.models.binding.CreateProjectBindingModel;
import example.awesomeproj.areas.user.entities.User;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {
    private String id;
    private User userCrate;
    private User userExecutor;
    private String text;
    private String textMin;
    private String creationDate;
    private String completionDate;
    private Set<Img> img;
    private boolean done;

    public Project() {
        this.done = false;
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
    @JoinColumn(name = "user_crate_id", referencedColumnName = "id")
    public User getUserCrate() {
        return userCrate;
    }

    public void setUserCrate(User userCrate) {
        this.userCrate = userCrate;
    }

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_executor_id", referencedColumnName = "id")
    public User getUserExecutor() {
        return userExecutor;
    }

    public void setUserExecutor(User userExecutor) {
        this.userExecutor = userExecutor;
    }

    @Column(length = 9999)
    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
        setTextMin(text);
    }

    public String getTextMin() {
        return textMin;
    }

    public void setTextMin(String textMin) {

        StringBuilder text = new StringBuilder();
        try {
            int count = 0;

            for (int i = 0; i < 20; i++) {
                if (textMin.charAt(i) == ' ') {
                    i--;
                }
                text.append(textMin.charAt(count));
                count++;
            }
        } catch (Exception e) {
        }


        this.textMin = text.toString();
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "many_img",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "img_id", referencedColumnName = "id"))
    public Set<Img> getImg() {
        return img;
    }

    public void setImg(Set<Img> img) {
        this.img = img;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.creationDate = formater.format(creationDate);
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

}
