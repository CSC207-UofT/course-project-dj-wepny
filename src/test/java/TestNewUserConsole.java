import consoleforgui.HelperConsole;
import consoleforgui.NewUserConsole;
import controllers.RunCommand;
import org.junit.Test;

public class TestNewUserConsole {

    @Test(timeout = 500)
    public void testCreateUser() {
        String[] basicUserInfo = {"username0991", "M"}; // user's name and gender
        String[] personalUserInfo = {"1.9", "101", "54"};   // user's height, weight, and age
        HelperConsole.createUser(basicUserInfo, personalUserInfo);

        RunCommand infoGetter = new RunCommand();
        assert "username0991".equals((String) infoGetter.retrieveUser("name"))
                && "101".equals((String) infoGetter.retrieveUser("weight"))
                && "1.9".equals((String) infoGetter.retrieveUser("height"))
                && "54".equals((String) infoGetter.retrieveUser("age"))
                && "M".equals((String) infoGetter.retrieveUser("gender"));
    }
}
