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

    //TODO
    /*private double calculateCost(){

        double companyCoefficient;
        double carTypeCoefficient;
        double serviceTypeCoefficient;

        switch (this.car.getCompany()){

            case BMW:
                companyCoefficient = 1.5;
                break;
            case BENZ:
                companyCoefficient = 2;
                break;

        }

    }*/
}
