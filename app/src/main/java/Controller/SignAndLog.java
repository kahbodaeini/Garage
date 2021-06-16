package Controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.simple.parser.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Model.User;

public class SignAndLog {

    private static FileWriter file;

    private boolean usernameExists(String username) throws IOException, ParseException {

        File myObj = new File("main/java/Model/Database/Usernames.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();

            if(username.equals(data))
                return true;
        }
        myReader.close();
        return false;
    }

    private void signup(User user) throws JSONException, IOException {

        JSONArray obj = user.toJson();
        file = new FileWriter("main/java/Model/Database/Users/"+user.getUserName()+".json");
        file.write(obj.toString());
    }

    private int checkPassword(String username, String password) throws IOException, ParseException, JSONException {

        if(!usernameExists(username))
            return 0;
        else{
            JSONParser jsonParser = new JSONParser();
            FileReader reader = new FileReader("main/java/Model/Database/Users/"+ username +".json");
            Object obj = jsonParser.parse(reader);

            JSONObject user = (JSONObject) obj;
            String pass = (String) user.get("password");

            if(pass.equals(password))
                return 1;
            else
                return -1;
        }

    }

}
