package Model;

import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class User {

    private String firstName, lastName, userName, password;
    private double budget;
    private Car[] cars;

    public User(String firstName, String lastName, String userName, String password, double budget, Car[] cars) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.budget = budget;
        this.cars = cars;
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

    public Car[] getCars() {
        return cars;
    }

    public void setCars(Car[] cars) {
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

        for(int i = 0; i < this.cars.length; i++){
            allCars.put(this.cars[i].toJson());
        }

        JSONArray user = new JSONArray();
        user.put(obj);
        user.put(allCars);
        return user;
    }
}
