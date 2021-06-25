package Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Car {

    private User owner;
    private Color color;
    private int year;
    private boolean intact;
    private Company company;
    private CarType type;
    private String sign;

    public Car(User owner, Color color, int year, boolean intact, Company company, CarType type, String sign) {
        this.owner = owner;
        this.color = color;
        this.year = year;
        this.intact = intact;
        this.company = company;
        this.type = type;
        this.sign = sign;
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
}