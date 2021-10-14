/* Below is the User class which stores user's information.
 */

package main.java;
import java.util.HashMap;


public class CreateUser {
    /**
     * A static function that creates and return a new User object
     * @param basic
     * @param personal
     * @return
     */
   public static User newUser(String[] basic, String[] personal){
       HashMap<String, Object> userInfo = new HashMap<String, Object>();

       userInfo.put("height", personal[0]);
       userInfo.put("weight", personal[1]);
       userInfo.put("age", personal[2]);

       User newUser = new User(basic[0], basic[1], userInfo);

       return newUser;
   }


}
