package controllers;

import java.util.List;
import models.*;

import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	List<Project> projects = Project.find.all();
    	List<Task> todoTasks = Task.find.all();
        return ok(index.render(projects,todoTasks));
    }
  
}
