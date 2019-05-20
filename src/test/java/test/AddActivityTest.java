package test;


import agenda.model.Activity;
import agenda.repository.classes.RepositoryActivityMock;
import agenda.repository.interfaces.RepositoryActivity;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddActivityTest {
	private Activity act;
	private RepositoryActivity repFile;
	
	@Before
	public void setUp() throws Exception {
		repFile = new RepositoryActivityMock();
	}
	
	@Test
	public void testCase1()
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try {
			act = new Activity("name1", 
					df.parse("03/20/2013 12:00"), 
					df.parse("03/20/2013 13:00"),
					null,
					"Lunch break");
			repFile.addActivity(act);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		assertTrue(1 == repFile.count());
	}
	
	@Test
	public void testCase2()
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try{
			for (Activity a : repFile.getActivities())
				repFile.removeActivity(a);
			
			act = new Activity("name1",
					df.parse("03/20/2013 12:00"), 
					df.parse("03/20/2013 13:00"),
					null,
					"Lunch break");
			repFile.addActivity(act);
			
			act = new Activity("name1",
					df.parse("03/21/2013 12:00"), 
					df.parse("03/21/2013 13:00"),
					null,
					"Lunch break");
			repFile.addActivity(act);
		}
		catch(Exception e){}	
		int c = repFile.count();
		assertTrue( c == 2);
		for (Activity a : repFile.getActivities())
			repFile.removeActivity(a);
	}
	
	@Test
	public void testCase3()
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try{
			for (Activity a : repFile.getActivities())
				repFile.removeActivity(a);
			
			act = new Activity("name1",
					df.parse("03/20/2013 12:00"), 
					df.parse("03/20/2013 13:00"),
					null,
					"Lunch break");
			repFile.addActivity(act);
			
			act = new Activity("name1",
					df.parse("03/20/2013 12:30"), 
					df.parse("03/20/2013 13:30"),
					null,
					"Lunch break");
			assertFalse(repFile.addActivity(act));
		}
		catch(Exception e){}	
		assertTrue( 1 == repFile.count());
		repFile.saveActivities();
		for (Activity a : repFile.getActivities())
			repFile.removeActivity(a);
	}
	
	@Test
	public void testCase4()
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try{
			for (Activity a : repFile.getActivities())
				repFile.removeActivity(a);
			
			act = new Activity("name1",
					df.parse("03/20/2013 12:00"), 
					df.parse("03/20/2013 13:00"),
					null,
					"Lunch break");
			repFile.addActivity(act);
			
			act = new Activity("name1",
					df.parse("03/20/2013 13:30"), 
					df.parse("03/20/2013 14:00"),
					null,
					"Curs");
			repFile.addActivity(act);
			
			act = new Activity("name1",
					df.parse("03/20/2013 13:30"), 
					df.parse("03/20/2013 14:30"),
					null,
					"Lunch break");
			assertFalse(repFile.addActivity(act));
		}
		catch(Exception e){}	
		assertTrue( 2 == repFile.count());
		for (Activity a : repFile.getActivities())
			repFile.removeActivity(a);
	}
	
	@Test
	public void testCase5()
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try{
			for (Activity a : repFile.getActivities())
				repFile.removeActivity(a);
			
			act = new Activity("name1",
					df.parse("03/20/2013 12:00"), 
					df.parse("03/20/2013 13:00"),
					null,
					"Lunch break");
			repFile.addActivity(act);
			
			assertFalse(repFile.addActivity(act));
		}
		catch(Exception e){}	
		assertTrue( 1 == repFile.count());
		for (Activity a : repFile.getActivities())
			repFile.removeActivity(a);
	}

}
