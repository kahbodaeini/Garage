package Controller;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Model.Car;
import Model.User;

public class SignAndLog {

    private static FileWriter file;
    public static User currentUser;
    public static Car currentCar;
    public static User guestUser;
    public static Car guestCar;


    public static boolean usernameExists(String username) throws IOException, ParseException {

//        File myObj = new File("main/java/Model/Database/Usernames.txt");
//        Scanner myReader = new Scanner(myObj);
//        while (myReader.hasNextLine()) {
//            String data = myReader.nextLine();
//
//            if(username.equals(data))
//                return true;
//        }
//        myReader.close();
//        return false;
        return User.getUserByUsername(username) != null;

    }

    public static void signup(String firstName, String lastName, String userName, String password, double budget) throws JSONException, IOException {

        User user = new User(firstName, lastName, userName, password, budget, new ArrayList<>());

//        JSONArray obj = user.toJson();
//        file = new FileWriter("main/java/Model/Database/Users/"+user.getUserName()+".json");
//        file.write(obj.toString());
//
//        FileWriter fileWriter = new FileWriter("main/java/Model/Database/Usernames.txt");
//        fileWriter.write(userName);

        currentUser = user;
    }

    public static int checkPassword(String username, String password) throws IOException, ParseException, JSONException {

        if(!usernameExists(username))
            return 0;
        else{
//            JSONParser jsonParser = new JSONParser();
//            FileReader reader = new FileReader("main/java/Model/Database/Users/"+ username +".json");
//            Object obj = jsonParser.parse(reader);
//
//            JSONObject user = (JSONObject) obj;
//            String pass = (String) user.get("password");

            User user = User.getUserByUsername(username);
            assert user != null;
            String pass = user.getPassword();

            if(pass.equals(password)){

//                String json = user.toString();
//                ObjectMapper objectMapper = new ObjectMapper();
//                currentUser = objectMapper.readValue(json, User.class);

                currentUser = user;
                return 1;
            }
            else
                return -1;
        }
    }

//    public static User getUserByUsername(String username) throws IOException, ParseException {
//
//        User returnedUser;
//
//        JSONParser jsonParser = new JSONParser();
//        FileReader reader = new FileReader("main/java/Model/Database/Users/"+ username +".json");
//        Object obj = jsonParser.parse(reader);
//
//        JSONObject user = (JSONObject) obj;
//
//        String json = user.toString();
//        ObjectMapper objectMapper = new ObjectMapper();
//        returnedUser = objectMapper.readValue(json, User.class);
//
//        return returnedUser;
//    }
}