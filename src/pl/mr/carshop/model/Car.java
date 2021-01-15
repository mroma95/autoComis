package pl.mr.carshop.model;

import java.util.Objects;

public class Car extends Vehicle {
    public static final String TYPE = "Samochód";
    private String engineType;
    private String colour;
    private double engineCapacity;
    private double price;

    public Car(String mark, String model, String vinNumber, int year, int wheelNumbers, int numberOfSeats, String engineType, String colour, double engineCapacity, double price) {
        super(mark, model, vinNumber, year, wheelNumbers, numberOfSeats);
        this.engineType = engineType;
        this.colour = colour;
        this.engineCapacity = engineCapacity;
        this.price = price;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
                engineType + ";" +
                colour + ";" +
                engineCapacity + ";" +
                price + "";
    }

    @Override
    public String toString() {
        return super.toString() +","+ engineType +
                "," + colour +
                "," + engineCapacity +
                "," + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return Double.compare(car.engineCapacity, engineCapacity) == 0 &&
                Double.compare(car.price, price) == 0 &&
                Objects.equals(engineType, car.engineType) &&
                Objects.equals(colour, car.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), engineType, colour, engineCapacity, price);
    }
}
