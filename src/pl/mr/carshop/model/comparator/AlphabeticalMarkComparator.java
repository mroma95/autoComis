package pl.mr.carshop.model.comparator;

import pl.mr.carshop.model.Vehicle;

import java.util.Comparator;

public class AlphabeticalMarkComparator implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle v1, Vehicle v2) {
        return v1.getMark().compareTo(v2.getMark());
    }
}
