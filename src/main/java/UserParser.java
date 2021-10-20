import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserParser {

    public static final String USER_FILE = "src/data/UserInfo.csv";

    public static void writeUserInfo(String info) throws IOException {
        FileWriter writeInfo;
        writeInfo = new FileWriter(USER_FILE);

        writeInfo.write(info + "\n");
        writeInfo.close();
    }

    public static ArrayList<String> readUserInfo() throws IOException {
        FileReader readInfo;
        readInfo = new FileReader(USER_FILE);
        ArrayList<String> UserInfo = new ArrayList<String>();
        BufferedReader br = new BufferedReader(readInfo);

        String currentUserInfo = br.readLine();
        while (currentUserInfo != null) {
            UserInfo.add(currentUserInfo);
            currentUserInfo = br.readLine();
        }
        br.close();
        readInfo.close();
        return UserInfo;

    }
}
