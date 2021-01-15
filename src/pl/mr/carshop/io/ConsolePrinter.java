package pl.mr.carshop.io;

import pl.mr.carshop.model.*;

import java.util.Collection;

public class ConsolePrinter {
    public void printCars(Collection<Vehicle> vehicles) {
        long counter= vehicles.stream()
                .filter(v->v instanceof Car)
                .map(Vehicle::toString)
                .peek(this::printLine)
                .count();
        if (counter == 0)
            printLine("Brak aut w komisie");
    }

    public void printMoto(Collection<Vehicle> vehicles) {
        long counter= vehicles.stream()
                .filter(v->v instanceof Motorcycle)
                .map(Vehicle::toString)
                .peek(this::printLine)
                .count();
        if (counter == 0)
            printLine("Brak motocykli w komisie");
    }

    public void printClients(Collection<ComisClient> clients){
        clients.stream()
                .map(Client::toString)
                .forEach(this::printLine);
    }
    public void printLine(String text) {
        System.out.println(text);
    }
}
