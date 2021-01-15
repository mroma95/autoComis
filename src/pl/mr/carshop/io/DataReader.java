package pl.mr.carshop.io;

import pl.mr.carshop.model.Car;
import pl.mr.carshop.model.ComisClient;
import pl.mr.carshop.model.Motorcycle;

import java.util.Locale;
import java.util.Scanner;

public class DataReader {
    private Scanner scanner = new Scanner(System.in);
    private ConsolePrinter printer;

    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }
    public String getString(){
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }

    public int getInt() {
        try {
            return scanner.nextInt();
        } finally {
            scanner.nextLine();
        }
    }

    public Car readAndCreateCar() {
        scanner.useLocale(Locale.US);
        System.out.println("Wprowadz markę:");
        String mark = scanner.nextLine();
        System.out.println("Wprowadz model:");
        String model = scanner.nextLine();
        System.out.println("Wprowadz vin:");
        String vin = scanner.nextLine();
        System.out.println("Wprowadz rodzaj paliwa:");
        String petrol = scanner.nextLine();
        System.out.println("Wprowadz kolor:");
        String colour = scanner.nextLine();
        System.out.println("Wprowadz pojemnośc silnika:");
        double engineCapacity = scanner.nextDouble();
        System.out.println("Wprowadz rok produkcji:");
        int year = getInt();
        System.out.println("Wprowadz cene:");
        double price = scanner.nextDouble();
        System.out.println("Wprowadz ilosc kół");
        int wheels = getInt();
        System.out.println("Wprowadz liczbe miejsc");
        int numberOfSeats = getInt();
        return new Car(mark, model, vin, year, wheels, numberOfSeats, petrol, colour, engineCapacity, price);
    }

    public Motorcycle readAndCreateMoto() {
        scanner.useLocale(Locale.US);
        System.out.println("Wprowadz markę:");
        String mark = scanner.nextLine();
        System.out.println("Wprowadz model:");
        String model = scanner.nextLine();
        System.out.println("Wprowadz vin:");
        String vin = scanner.nextLine();
        System.out.println("Wprowadz rodzaj ramy motocyklowej:");
        String frame = scanner.nextLine();
        System.out.println("Wprowadz rok produkcji:");
        int year = getInt();
        System.out.println("Wprowadz ilosc kół");
        int wheels = getInt();
        System.out.println("Wprowadz liczbe miejsc");
        int numberOfSeats = getInt();
        return new Motorcycle(mark, model, vin, year, wheels, numberOfSeats, frame);
    }
    public ComisClient createComisClient(){
        printer.printLine("Imię");
        String firstName = scanner.nextLine();
        printer.printLine("Nazwisko");
        String lastName = scanner.nextLine();
        printer.printLine("Pesel");
        String pesel = scanner.nextLine();
        return new ComisClient(firstName, lastName, pesel);
    }
}
