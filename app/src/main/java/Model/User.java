package Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class User {

    private String firstName, lastName, userName, password, about, imagePath;
    private double budget;
    private ArrayList<Car> cars = new ArrayList<>();
    private static final ArrayList<User> allUsers = new ArrayList<>();

    public User(String firstName, String lastName, String userName, String password, double budget) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.budget = budget;
        allUsers.add(this);
    }

    public static ArrayList<User> getAllUsers(){
        return allUsers;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws IOException {
        this.firstName = firstName;
//        File file = new File("main/java/Model/Database/Users/"+this.getUserName()+".json");
//        file.deleteOnExit();
//
//        FileWriter newFile = new FileWriter("main/java/Model/Database/Users/"+this.getUserName()+".json");
//        newFile.write(this.toString());
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws IOException {
        this.lastName = lastName;
//        File file = new File("main/java/Model/Database/Users/"+this.getUserName()+".json");
//        file.deleteOnExit();
//
//        FileWriter newFile = new FileWriter("main/java/Model/Database/Users/"+this.getUserName()+".json");
//        newFile.write(this.toString());
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) throws IOException {
        this.userName = userName;
//        File file = new File("main/java/Model/Database/Users/"+this.getUserName()+".json");
//        file.deleteOnExit();
//
//        FileWriter newFile = new FileWriter("main/java/Model/Database/Users/"+this.getUserName()+".json");
//        newFile.write(this.toString());
    }

    public String getPassword() {
        return password;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) throws IOException {
        this.budget = budget;
//        File file = new File("main/java/Model/Database/Users/"+this.getUserName()+".json");
//        file.deleteOnExit();
//
//        FileWriter newFile = new FileWriter("main/java/Model/Database/Users/"+this.getUserName()+".json");
//        newFile.write(this.toString());
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

//        File file = new File("main/java/Model/Database/Users/"+this.getUserName()+".json");
//        file.deleteOnExit();
//
//        FileWriter newFile = new FileWriter("main/java/Model/Database/Users/"+this.getUserName()+".json");
//        newFile.write(this.toString());
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) throws IOException {
        this.about = about;
//        File file = new File("main/java/Model/Database/Users/"+this.getUserName()+".json");
//        file.deleteOnExit();
//
//        FileWriter newFile = new FileWriter("main/java/Model/Database/Users/"+this.getUserName()+".json");
//        newFile.write(this.toString());
    }

    public void removeCar(Car car) throws IOException {
        cars.remove(getCarBySign(car.getSign()));
        cars.clear();

//        ArrayList<Car> cars = this.getCars();
//        cars.remove(car);
//        this.setCars(cars);

//        File file = new File("main/java/Model/Database/Users/"+this.getUserName()+".json");
//        file.deleteOnExit();
//
//        FileWriter newFile = new FileWriter("main/java/Model/Database/Users/"+this.getUserName()+".json");
//        newFile.write(this.toString());

    }

    public Car getCarBySign(String sign){
        for (Car car : cars) {
            if (car.getSign().equals(sign)){
                return car;
            }
        }
        return null;
    }

    public boolean checkPasswordInProfilePage(String password){

        return this.password.equals(password);
    }

    public boolean changePassword(String oldPassword, String newPassword) throws IOException {

        if (checkPasswordInProfilePage(oldPassword))
            return false;
        else{
            this.password = newPassword;
//            File file = new File("main/java/Model/Database/Users/"+this.getUserName()+".json");
//            file.deleteOnExit();
//
//            FileWriter newFile = new FileWriter("main/java/Model/Database/Users/"+this.getUserName()+".json");
//            newFile.write(this.toString());
            return true;
        }
    }

    public static User getUserByUsername(String username){
        for(int i = 0; i < allUsers.size(); i++){
            if(username.equals(allUsers.get(i).getUserName()))
                return allUsers.get(i);
        }
        return null;
    }
}
