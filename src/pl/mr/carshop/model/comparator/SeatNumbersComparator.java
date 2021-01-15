package pl.mr.carshop.model.comparator;

import pl.mr.carshop.model.Vehicle;

import java.util.Comparator;

public class SeatNumbersComparator implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle v1, Vehicle v2) {
        if (v1==null && v2==null)
            return 0;
        else if (v1==null)
            return 1;
        else if (v2==null)
            return -1;
        return Integer.compare(v1.getNumberOfSeats(),v2.getNumberOfSeats());
    }
}
