package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import play.db.ebean.*;

@Entity
public class Project extends Model{
	@Id
	public Long id;
	public String name;
	public String folder;
	@ManyToMany(cascade=CascadeType.REMOVE)
	public List<User> members = new ArrayList<>();
	
	public Project(String name, String folder, User owner){
		this.name = name;
		this.folder = folder;
		this.members.add(owner);
	}
	
	public static Finder<Long,Project> find = new Finder<>(long.class,Project.class);
	
	public static Project create(String name,String folder,String owner){
		Project project = new Project(name,folder,User.find.ref(owner));
		project.save();
		project.saveManyToManyAssociations("members");
		return project;
	}
	
	public static List<Project> findInvolving(String user){
		return find.where().eq("members.email",user).findList();
	}
	
	public static List<Project> all(){
		return new ArrayList<Project>();
	}	
}
