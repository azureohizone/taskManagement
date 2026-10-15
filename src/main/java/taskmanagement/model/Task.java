package taskmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   

    private Long id;  // using Long because it allowed null while long isn't.
    private String title;
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task(){}
    //JPA/Hibernate needs a no-argument constructor 
    // so it can create objects when retrieving 
    // records from the database.

    

    public Task(Long id, String title, String status){
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public Long getID(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getStatus(){
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
    return user;
}

    public void setUser(User user) {
    this.user = user;
}
}
