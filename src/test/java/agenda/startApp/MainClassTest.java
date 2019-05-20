package agenda.startApp;

import agenda.controller.ServiceActivity;
import agenda.controller.ServiceContact;
import agenda.model.Activity;
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

public class MainClassTest {
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
    public void afisActivitate_F03_TC01() {
        InputStream testIn = new ByteArrayInputStream("03/20/2013".getBytes());
        try {
            MainClass.afisActivitate(activityService, new BufferedReader(new InputStreamReader(testIn)), user);
            assert true;
        } catch (Exception e) {
            assert false;
        }

    }

    @Test
    public void afisActivitate_F03_TC02() {
        InputStream testIn = new ByteArrayInputStream("03//20/2013".getBytes());
        try {
            MainClass.afisActivitate(activityService, new BufferedReader(new InputStreamReader(testIn)), user);
            assert false;
        } catch (Exception e) {
            assert true;
        }
    }
}