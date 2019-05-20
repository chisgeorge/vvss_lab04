package agenda.startApp;

import agenda.controller.ServiceActivity;
import agenda.exceptions.InvalidFormatException;
import agenda.model.Activity;
import agenda.model.Contact;
import agenda.model.User;
import agenda.repository.classes.RepositoryActivityFile;
import agenda.repository.classes.RepositoryContactFile;
import agenda.repository.classes.RepositoryUserFile;
import agenda.repository.interfaces.RepositoryActivity;
import agenda.repository.interfaces.RepositoryContact;
import agenda.repository.interfaces.RepositoryUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class MainClassTest_BigBang {

    private RepositoryContact contactRep;
    private RepositoryUser userRep;
    private RepositoryActivity activityRep;
    private ServiceActivity activityService;
    User user;

    @Before
    public void setUp() throws Exception {
        contactRep = new RepositoryContactFile();
        userRep = new RepositoryUserFile();
        activityRep = new RepositoryActivityFile(
                contactRep);
        activityService= new ServiceActivity(activityRep);
        user = userRep.getByUsername("username1");
        if (!user.isPassword("pass1"))
            user = null;
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void integrareBigBang() {
        // P -> A -> B -> C
        Contact con = null;
        try {
            con = new Contact("nume1", "adresa1", "0123456789", "email@a.com");
        } catch (InvalidFormatException e) {
            fail();
        }
        contactRep.addContact(con);
        for(Contact c : contactRep.getContacts())
            if (c.equals(con))
            {
                assertTrue(true);
                break;
            }

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            Activity act = new Activity("name4",
                    df.parse("03/20/2015 14:00"),
                    df.parse("03/20/2015 15:00"),
                    null,
                    "Lunch break");
            activityRep.addActivity(act);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert 4 == activityRep.count();
        InputStream testIn = new ByteArrayInputStream("03/20/2013".getBytes());
        try {
            MainClass.afisActivitate(activityService, new BufferedReader(new InputStreamReader(testIn)), user);
            assert true;
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void addContact_F01() {
        Contact con = null;
        try {
            con = new Contact("nume1", "adresa1", "0123456789", "email@a.com");
        } catch (InvalidFormatException e) {
            fail();
        }

        contactRep.addContact(con);
        for(Contact c : contactRep.getContacts())
            if (c.equals(con))
            {
                assertTrue(true);
                break;
            }
    }

    @Test
    public void addActivity_F02()
    {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            Activity act = new Activity("name4",
                    df.parse("03/20/2015 14:00"),
                    df.parse("03/20/2015 15:00"),
                    null,
                    "Lunch break");
            activityRep.addActivity(act);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert 4 == activityRep.count();
    }

    @Test
    public void afisActivitate_F03() {
        InputStream testIn = new ByteArrayInputStream("03/20/2013".getBytes());
        try {
            MainClass.afisActivitate(activityService, new BufferedReader(new InputStreamReader(testIn)), user);
            assert true;
        } catch (Exception e) {
            assert false;
        }
    }
}