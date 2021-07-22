package Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class User {

    private String firstName, lastName, userName, password;
    private double budget;
    private ArrayList<Car> cars;
    private static ArrayList<User> allUsers = new ArrayList<>();

    public User(String firstName, String lastName, String userName, String password, double budget, ArrayList<Car> cars) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.budget = budget;
        this.cars = cars;
        allUsers.add(this);
    }

    public static ArrayList<User> getAllUsers(){
        return allUsers;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public JSONArray toJson() throws JSONException {

        JSONObject obj = new JSONObject();
        obj.put("firstName", this.getFirstName());
        obj.put("lastName", this.getLastName());
        obj.put("userName", this.getUserName());
        obj.put("password", this.getPassword());
        obj.put("budget", this.getBudget());

        JSONArray allCars = new JSONArray();

        for(int i = 0; i < this.cars.size(); i++){
            allCars.put(this.cars.get(i).toJson());
        }

        JSONArray user = new JSONArray();
        user.put(obj);
        user.put(allCars);
        return user;
    }

    public void addCar(Car car) throws IOException {

        ArrayList<Car> cars = this.getCars();
        cars.add(car);
        this.setCars(cars);

        File file = new File("main/java/Model/Database/Users/"+this.getUserName()+".json");
        file.deleteOnExit();

        FileWriter newFile = new FileWriter("main/java/Model/Database/Users/"+this.getUserName()+".json");
        newFile.write(this.toString());

    }

    public void removeCar(Car car) throws IOException {

        ArrayList<Car> cars = this.getCars();
        cars.remove(car);
        this.setCars(cars);

        File file = new File("main/java/Model/Database/Users/"+this.getUserName()+".json");
        file.deleteOnExit();

        FileWriter newFile = new FileWriter("main/java/Model/Database/Users/"+this.getUserName()+".json");
        newFile.write(this.toString());

    }

    public boolean checkPasswordInProfilePage(String password){

        return this.password.equals(password);
    }

    public boolean changePassword(String oldPassword, String newPassword) throws IOException {

        if (checkPasswordInProfilePage(oldPassword))
            return false;
        else{
            this.password = newPassword;
            File file = new File("main/java/Model/Database/Users/"+this.getUserName()+".json");
            file.deleteOnExit();

            FileWriter newFile = new FileWriter("main/java/Model/Database/Users/"+this.getUserName()+".json");
            newFile.write(this.toString());
            return true;
        }
    }
}
