package Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Car {

    private User owner;
    private Color color;
    private int year;
    private boolean intact;
    private Company company;
    private CarType type;
    private String sign;
    private static ArrayList<Car> allCars = new ArrayList<>();


    public Car(User owner, Color color, int year, boolean intact, Company company, CarType type, String sign) {
        this.owner = owner;
        this.color = color;
        this.year = year;
        this.intact = intact;
        this.company = company;
        this.type = type;
        this.sign = sign;
        allCars.add(this);
    }

    public static ArrayList<Car> getAllCars(){
        return allCars;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isIntact() {
        return intact;
    }

    public void setIntact(boolean intact) {
        this.intact = intact;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("company", this.getCompany());
        obj.put("color", this.getColor());
        obj.put("year", this.getYear());
        obj.put("type", this.getType());
        obj.put("sign", this.getSign());

        if(this.isIntact()){
            obj.put("intact","Yes");
        }
        else
            obj.put("intact", "No");
        return obj;
    }

    public double calculatePrice(){

        double companyCoefficient;
        double carTypeCoefficient;
        double intactCoefficient;

        intactCoefficient = this.intact ? 0 : -2;

        switch (this.getCompany()){

            case BMW:
            case CADILLAC:
            case AUDI:
            case BENZ:
                companyCoefficient = 8;
                break;
            case FIAT:
                companyCoefficient = 6;
                break;
            case JAGUAR:
            case TESLA:
            case ASTON_MARTIN:
            case PORSCHE:
                companyCoefficient = 9;
                break;
            case JEEP:
            case VOLKSWAGEN:
            case VOLVO:
            case FORD:
                companyCoefficient = 5;
                break;
            case PEUGEOT:
                companyCoefficient = 3;
                break;
            case BUGATTI:
            case MASERATI:
            case LAMBORGHINI:
            case FERRARI:
                companyCoefficient = 10;
                break;
            case LEXUS:
                companyCoefficient = 7;
                break;
            case TOYOTA:
            case CHEVROLET:
            case MITSUBISHI:
            case SUBARU:
            case MAZDA:
            case HONDA:
            case NISSAN:
            default:
                companyCoefficient = 4;
                break;
        }

        switch (this.getType()){
            case SUPERCAR:
                carTypeCoefficient = 7;
                break;
            case MICRO:
                carTypeCoefficient = 1;
                break;
            case PICKUP:
            case TRUCK:
            case SUV:
                carTypeCoefficient = 4;
                break;
            case COUPE:
            case HATCHBACK:
                carTypeCoefficient = 2;
                break;
            case VAN:
                carTypeCoefficient = 5;
                break;

            case CUV:
            case SEDAN:
            default:
                carTypeCoefficient = 3;
                break;
        }

        return 3000 * (carTypeCoefficient + companyCoefficient + intactCoefficient);
    }

    public String getCompanyColor(){

        String color;
        Company company = this.getCompany();

        switch (company){

            case ASTON_MARTIN:
                color = "#A9A9A9";
            case LAMBORGHINI:
                color = "##B22222";
            case VOLKSWAGEN:
                color = "#1E90FF";
            case CHEVROLET:
            case PORSCHE:
                color = "#FFD700";
            case MASERATI:
                color = "#87CEFA";
            case CADILLAC:
                color = "#FFA500";
            case PEUGEOT:
                color = "#0000FF";
            case FERRARI:
                color = "#8B0000";
            case BUGATTI:
                color = "#800000";
            case TOYOTA:
            case NISSAN:
                color = "#D3D3D3";
            case SUBARU:
                color = "#0000CD";
            case JAGUAR:
                color = "#708090";
            case VOLVO:
                color = "#FFFAFA";
            case MAZDA:
            case LEXUS:
            case AUDI:
                color = "#D3D3D3";
            case HONDA:
                color = "#B22222";
            case JEEP:
                color = "#FFFAF0";
            case FORD:
                color = "#00008B";
            case FIAT:
                color = "#DC143C";
            case TESLA:
            case MITSUBISHI:
                color = "#FF4500";
            case BMW:
                color = "#0000FF";
            case BENZ:
            default:
                color = "#C0C0C0";
        }
        return color;
    }
}