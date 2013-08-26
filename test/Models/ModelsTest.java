package Models;

import java.util.List;

import models.*;

import org.junit.*;
import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;

public class ModelsTest extends WithApplication {
	@Before
	public void setUp(){
		start(fakeApplication(inMemoryDatabase()));
	}
	
	@Test
	public void createAndRetrieveUser(){
		new User("Albert Fiati","awkfiati@gmail.com","albert").save();
		User albert = User.find.where().eq("email","awkfiati@gmail.com").findUnique();
		assertNotNull(albert);
		assertEquals("Albert Fiati", albert.name);		
	}
	
	@Test
	public void authenticateUser(){
		new User("Albert Fiati","awkfiati@gmail.com","albert").save();
		User validUser = User.authenticate("awkfiati@gmail.com","albert");
		assertNotNull(validUser);
	}
	
	@Test
	public void findProjectsInvolving(){
		new User("albert fiati","albert@gmail.com", "albert").save();
		new User("Edward fiati","edward@gmail.com", "edward").save();
		
		Project p1 = Project.create("Bookshelve", "Play", "albert@gmail.com");
		Project p4 = Project.create("Guest Login", "Play", "albert@gmail.com");
		
		List<Project> result = Project.findInvolving("albert@gmail.com");	
		
		assertEquals(2, result.size());
		assertNotNull(result);
	}
	
	@Test
    public void findTodoTasksInvolving() {
        User bob = new User("Bob","bob@gmail.com", "secret");
        bob.save();

        Project project = Project.create("Play 2", "play", "bob@gmail.com");
        assertNotNull(project);        
        
        Task t1 = new Task();
        t1.title = "Write tutorial";
        t1.assignedTo = bob;
        t1.done = true;
        t1.save();
        

        Task t2 = new Task();
        t2.title = "Release next version";
        t2.project = project;
        t2.save();

        List<Task> results = Task.findTodoInvolving("bob@gmail.com");
        assertEquals(1, results.size());
        assertEquals("Release next version", results.get(0).title);
    }
}
