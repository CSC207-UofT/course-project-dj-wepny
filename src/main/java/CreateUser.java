import java.util.HashMap;
import java.lang.Math;
/**
 * Below is the User class which stores user's information.
 */
public class CreateUser {
    /**
     * A static function that creates and return a new User object
     * @param basic is an arraylist of name and gender
     * @param personal is an arraylist of weight, height, and age
     * @return a new User object
     */
    public static User newUser(String[] basic, String[] personal){
        HashMap<String, Object> userInfo = new HashMap<String, Object>();

        userInfo.put("height", personal[0]);
        userInfo.put("weight", personal[1]);
        userInfo.put("age", personal[2]);

        int id = (int)Math.floor(Math.random()*(10000-1+1)+1);

        return new User(id, basic[0], basic[1], userInfo);
    }
}
