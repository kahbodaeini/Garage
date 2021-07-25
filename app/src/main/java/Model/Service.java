package Model;

public class Service {

    private Car car;
    private ServiceType serviceType;

    public Service(Car car, ServiceType serviceType) {
        this.car = car;
        this.serviceType = serviceType;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public double calculateCost(){

        double companyCoefficient;
        double carTypeCoefficient;
        double serviceTypeCoefficient;

        switch (this.car.getCompany()){

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

        switch (this.getServiceType()){

            case COLOR:
            case ENGINE1:
                serviceTypeCoefficient = 5;
                break;
            case RING1:
            case LEATHER1:
            case LIGHT2:
            case EXHAUST2:
                serviceTypeCoefficient = 2;
                break;
            case RING2:
            case LEATHER2:
            case EXHAUST3:
                serviceTypeCoefficient = 3;
                break;
            case RING3:
            case LEATHER3:
                serviceTypeCoefficient = 4;
                break;
            case LIGHT1:
            case EXHAUST1:
                serviceTypeCoefficient = 1.5;
                break;
            case LIGHT3:
                serviceTypeCoefficient = 2.5;
                break;
            case ENGINE2:
            case REPAIRMENT1:
                serviceTypeCoefficient = 6;
                break;
            case ENGINE3:
            case REPAIRMENT2:
                serviceTypeCoefficient = 7;
                break;
            case REPAIRMENT3:
                serviceTypeCoefficient = 8;
                break;
            case REPAIRMENT4:
                serviceTypeCoefficient = 9;
                break;
            case REPAIRMENT5:
                serviceTypeCoefficient = 10;
                break;
            case CAR_WASH:
            default:
                serviceTypeCoefficient = 1;
                break;
        }

        switch (this.car.getType()){
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

        return 500*carTypeCoefficient + 1000 * serviceTypeCoefficient + 1500 * companyCoefficient;
    }

    public static String getImagePath(ServiceType serviceType){

        String string = null;

        switch (serviceType){

            case EXHAUST1:
                string = "/src/main/java/Model/images/Exhaust1.jpg";
            case EXHAUST2:
                string = "/src/main/java/Model/images/Exhaust2.jpeg";
            case EXHAUST3:
                string = "/src/main/java/Model/images/Exhaust3.jpg";
            case RING1:
                string = "/src/main/java/Model/images/Tire1.jpg";
            case RING2:
                string = "/src/main/java/Model/images/Tire2.jpg";
            case RING3:
                string = "/src/main/java/Model/images/Tire3.jpg";
            case LEATHER1:
                string = "/src/main/java/Model/images/Leather1.jpg";
            case LEATHER2:
                string = "/src/main/java/Model/images/Leather2.jpg";
            case LEATHER3:
                string = "/src/main/java/Model/images/Leather3.jpeg";
            case LIGHT1:
                string = "/src/main/java/Model/images/Light1.jpg";
            case LIGHT2:
                string = "/src/main/java/Model/images/Light2.jpg";
            case LIGHT3:
                string = "/src/main/java/Model/images/Light3.jpg";
        }
        return string;
    }
}
