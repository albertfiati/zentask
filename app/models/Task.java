package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import play.db.ebean.*;
import scala.Array;

@Entity
public class Task extends Model {
	@Id
	public Long id;
	public String title;
	public String folder;
	public Boolean done = false;
	public Date dueDate;
	@ManyToOne
	public User assignedTo;
	@ManyToOne
	public Project project;
	
	public static Finder<Long,Task> find = new Finder<>(Long.class, Task.class);	

    public static List<Task> findTodoInvolving(String user) {
       return find.fetch("project").where()
                .eq("done", false)
                .eq("project.members.email", user)
           .findList();
    }

    public static Task create(Task task, Long project, String folder) {
        task.project = Project.find.ref(project);
        task.folder = folder;
        task.save();
        return task;
    }	
    
    public static List<Task> all(){
    	return new ArrayList<Task>();
    }
}
