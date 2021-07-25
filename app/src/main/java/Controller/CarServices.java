package Controller;

import java.io.IOException;

import Model.*;

public class CarServices {

    private final Car car;

    public CarServices(Car car){
        this.car = car;
    }

    public void enterCar(Color color, int year, boolean intact, Company company, CarType type, String sign) throws IOException {

        Car car = new Car(SignAndLog.currentUser, color, year, intact, company, type, sign);
        SignAndLog.currentUser.addCar(car);
    }

    public void upperTheBudget(double budget) throws IOException {
        SignAndLog.currentUser.setBudget(SignAndLog.currentUser.getBudget() + budget);
    }

    public boolean doService(Service service) throws IOException {

        double cost = service.calculateCost();

        if(SignAndLog.currentUser.getBudget() >= cost){

            if (service.getServiceType() == ServiceType.COLOR1)
                service.getCar().setColor(Color.WHITE);
            if (service.getServiceType() == ServiceType.COLOR2)
                service.getCar().setColor(Color.BLACK);
            if (service.getServiceType() == ServiceType.COLOR3)
                service.getCar().setColor(Color.RED);

            SignAndLog.currentUser.setBudget(SignAndLog.currentUser.getBudget() - cost);
            return true;
        }
        else
            return false;
    }
    
    public static ServiceType calculateLevelOfRepairment(int percent){
        
        if(0 < percent && percent < 10)
            return ServiceType.REPAIRMENT1;

        else if( 10 <= percent && percent < 30)
            return ServiceType.REPAIRMENT2;

        else if( 30 <= percent && percent < 50)
            return ServiceType.REPAIRMENT3;

        else if( 50 <= percent && percent < 70)
            return ServiceType.REPAIRMENT4;

        else
            return ServiceType.REPAIRMENT5;
    }
}
