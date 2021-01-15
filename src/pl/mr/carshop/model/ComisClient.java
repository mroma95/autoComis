package pl.mr.carshop.model;

import java.util.*;

public class ComisClient extends Client {
    private Set<Vehicle> interestedVehiclesHistory=new HashSet<>();

    public ComisClient(String firstName, String lastName, String pesel) {
        super(firstName, lastName, pesel);
    }

    @Override
    public String toCsv() {
        return getFirstName()+";"+getLastName()+";"+getPesel();
    }

    public Set<Vehicle> getInterestedVehiclesHistory() {
        return interestedVehiclesHistory;
    }
    public void addInterestedVehcileHistory(Vehicle vehicle){
        interestedVehiclesHistory.add(vehicle);
    }
//    public boolean changeInterestedVehicle(Vehicle vehicle){
//        boolean result=false;
//        if (interestedVehiclesHistory.size()==1){
//            result=true;
//            interestedVehiclesHistory.
//        }
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ComisClient that = (ComisClient) o;
        return Objects.equals(interestedVehiclesHistory, that.interestedVehiclesHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), interestedVehiclesHistory);
    }
}
