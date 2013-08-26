package models;

import javax.persistence.*;
import play.db.ebean.*;

import java.util.*;

@Entity
public class User extends Model {
	@Id
	public String email;
	public String name;	
	public String password;
	
	public User(String name, String email, String password){
		this.name = name;
		this.email = email;
		this.password = password;
	}	
		
	public static List<User> all(){
		return new ArrayList<User>();
	}
	
	public static Finder<String, User> find = new Finder<>(String.class,User.class);
		
	public static User authenticate(String email,String password){
		return User.find.where().eq("email",email).eq("password", password).findUnique();
	}
}
