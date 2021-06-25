package Controller;

import java.io.IOException;

import Model.*;

public class CarServices {

    public void enterCar(Color color, int year, boolean intact, Company company, CarType type, String sign) throws IOException {

        Car car = new Car(SignAndLog.currentUser, color, year, intact, company, type, sign);
        SignAndLog.currentUser.addCar(car);
    }

    public void upperTheBudget(double budget){
        SignAndLog.currentUser.setBudget(SignAndLog.currentUser.getBudget() + budget);
    }

    public boolean doService(Service service){

        double cost = service.calculateCost();

        if(SignAndLog.currentUser.getBudget() >= cost){

            SignAndLog.currentUser.setBudget(SignAndLog.currentUser.getBudget() - cost);
            return true;
        }
        else
            return false;
    }

    public void sellCar(Car car) throws IOException {

        double price = car.calculatePrice();
        SignAndLog.currentUser.setBudget(SignAndLog.currentUser.getBudget() + price);
        SignAndLog.currentUser.removeCar(car);

    }
    
    public ServiceType calculateLevelOfRepairment(int percent){
        
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
