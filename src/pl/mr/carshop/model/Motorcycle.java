package pl.mr.carshop.model;

import java.util.Objects;

public class Motorcycle extends Vehicle{
    public static final String TYPE="Motocykl";
    private String motocycleFrame;

    public Motorcycle(String mark, String model, String vinNumber, int year, int wheelNumbers, int numberOfSeats, String motocycleFrame) {
        super(mark, model, vinNumber, year, wheelNumbers, numberOfSeats);
        this.motocycleFrame = motocycleFrame;
    }

    public String getMotocycleFrame() {
        return motocycleFrame;
    }

    public void setMotocycleFrame(String motocycleFrame) {
        this.motocycleFrame = motocycleFrame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Motorcycle that = (Motorcycle) o;
        return Objects.equals(motocycleFrame, that.motocycleFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), motocycleFrame);
    }

    @Override
    public String toCsv() {
        return (TYPE + ";") +
                getMark() + ";" +
                getModel() + ";" +
                getVinNumber() + ";" +
                getYear() + ";" +
                getWheelNumbers() + ";" +
                getNumberOfSeats() + ";" +
                motocycleFrame+"";
    }

    @Override
    public String toString() {
        return super.toString()+","+motocycleFrame;

    }
}
