package pl.mr.carshop.model;

import pl.mr.carshop.exception.ClientAlreadyExistException;

import java.io.Serializable;
import java.util.*;

public class Comis implements Serializable {
    private Map<String,Vehicle> vehicles = new HashMap<>();
    private Map<String,ComisClient> clients=new HashMap<>();

    public Map<String,Vehicle> getVehicles(){
        return vehicles;
    }
    public Map<String,ComisClient> getClient(){return clients;}

    public Optional<Vehicle> findVehicleByVin(String vin){
        return Optional.ofNullable(vehicles.get(vin));
    }
    public Collection<Vehicle> getSortedVehicles(Comparator<Vehicle> comparator){
        ArrayList<Vehicle> list=new ArrayList<>(this.vehicles.values());
        list.sort(comparator);
        return list;
    }
    public Collection<ComisClient> getSortedClients(Comparator<ComisClient> comparator){
        ArrayList<ComisClient> list=new ArrayList<>(this.clients.values());
        list.sort(comparator);
        return list;
    }

    public void addClient(ComisClient client){
        if (clients.containsKey(client.getPesel())){
            throw new ClientAlreadyExistException("Klient o podanym numerze pesel jest już w bazie danych");
        }
        clients.put(client.getPesel(),client);
    }
    public void addVehicle(Vehicle vehicle){
        if (vehicles.containsKey(vehicle.getVinNumber())){
            System.out.println("Pojazd o podanym numerze VIN juz istnieje");
        }
        vehicles.put(vehicle.getVinNumber(),vehicle);
    }
    public boolean removeVehicle(Vehicle vehicle){
        if (vehicles.containsValue(vehicle)){
            vehicles.remove(vehicle.getVinNumber());
            return true;
        }else {
            return false;
        }
    }

}
