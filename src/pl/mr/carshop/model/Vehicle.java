package pl.mr.carshop.model;

import java.io.Serializable;
import java.time.Year;
import java.util.Objects;

public abstract class Vehicle implements Serializable,Comparable<Vehicle>,CsvConvertable {
    private String mark;
    private String model;
    private String vinNumber;
    private Year year;
    private int wheelNumbers;
    private int numberOfSeats;

    public abstract String toCsv();

    @Override
    public int compareTo(Vehicle v) {
        return mark.compareTo(v.mark);
    }

    public Vehicle(String mark, String model, String vinNumber, int year, int wheelNumbers, int numberOfSeats) {
        this.mark = mark;
        this.model = model;
        this.vinNumber = vinNumber;
        this.year = Year.of(year);
        this.wheelNumbers = wheelNumbers;
        this.numberOfSeats = numberOfSeats;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public int getWheelNumbers() {
        return wheelNumbers;
    }

    public void setWheelNumbers(int wheelNumbers) {
        this.wheelNumbers = wheelNumbers;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return mark+","+model+","+vinNumber+","+year+","+wheelNumbers+","+numberOfSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return wheelNumbers == vehicle.wheelNumbers &&
                numberOfSeats == vehicle.numberOfSeats &&
                Objects.equals(mark, vehicle.mark) &&
                Objects.equals(model, vehicle.model) &&
                Objects.equals(vinNumber, vehicle.vinNumber) &&
                Objects.equals(year, vehicle.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark, model, vinNumber, year, wheelNumbers, numberOfSeats);
    }
}
