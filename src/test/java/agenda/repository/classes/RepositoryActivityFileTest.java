package agenda.repository.classes;

import agenda.model.Activity;
import agenda.repository.interfaces.RepositoryActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class RepositoryActivityFileTest {

    private Activity act;
    private RepositoryActivityFile repositoryActivityFile;
    private RepositoryContactFile repositoryContactFile;

    @Before
    public void setUp() throws Exception {
        repositoryContactFile = new RepositoryContactFile();
        repositoryActivityFile = new RepositoryActivityFile(repositoryContactFile);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addActivity_F02_TC01() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {

            act = new Activity("name1",
                    df.parse("01/01/2018 12:00"),
                    df.parse("01/01/2017 13:00"),
                    null,
                    "Activity description");

            assertFalse(repositoryActivityFile.addActivity(act));
        } catch (Exception e) {
        }
    }

    @Test
    public void addActivity_F02_TC03() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {

            act = new Activity("name1",
                    df.parse("01/01/2012 12:00"),
                    df.parse("01/01/2017 13:00"),
                    null,
                    "Activity description");

            assertFalse(repositoryActivityFile.addActivity(act));
        } catch (Exception e) {
        }
    }

    @Test
    public void addActivity_F02_TC02() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {

            act = new Activity("name1",
                    df.parse("01/01/2017 12:00"),
                    df.parse("01/01/2018 13:00"),
                    null,
                    "Activity description");
            assertTrue(repositoryActivityFile.addActivity(act));
        } catch (Exception e) {
        }
    }
}