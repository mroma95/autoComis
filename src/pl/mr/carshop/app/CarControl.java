package pl.mr.carshop.app;

import pl.mr.carshop.exception.ClientAlreadyExistException;
import pl.mr.carshop.exception.DataExportException;
import pl.mr.carshop.exception.DataImportException;
import pl.mr.carshop.exception.NoSuchOptionException;
import pl.mr.carshop.io.ConsolePrinter;
import pl.mr.carshop.io.DataReader;
import pl.mr.carshop.io.file.FileManager;
import pl.mr.carshop.io.file.FileManagerBuilder;
import pl.mr.carshop.model.*;

import java.util.Comparator;
import java.util.InputMismatchException;


public class CarControl {

    private Comis comis;
    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;

    CarControl() {
        fileManager = new FileManagerBuilder(printer, dataReader).build();
        try {
            comis = fileManager.importData();
            printer.printLine("Zaimportowane dane z pliku");
        } catch (DataImportException e) {
            printer.printLine(e.getMessage());
            printer.printLine("Zainicjowano nową bazę.");
            comis = new Comis();
        }

    }

    public void controlLoop() {
        Option option;
        do {
            printOptions();
            option = getOption();
            switch (option) {
                case ADD_CAR:
                    addCar();
                    break;
                case SHOW_CARS:
                    showCars();
                    break;
                case ADD_MOTO:
                    addMoto();
                    break;
                case SHOW_MOTO:
                    showMoto();
                    break;
                case DELETE_CAR:
                    deleteCar();
                    break;
                case DELETE_MOTO:
                    deleteMoto();
                    break;
                case ADD_CLIENT:
                    addClient();
                    break;
                case SHOW_CLIENTS:
                    showClients();
                    break;
                case SEARCH_VEHICLE:
                    searchVehicle();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    System.out.println("Nie ma takiej opcji!");
            }
        } while (option != Option.EXIT);
    }



    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage());
            } catch (InputMismatchException e) {
                printer.printLine("Wprowadzono wartosc ktora nie jest liczba");
            }
        }
        return option;
    }

    private void printOptions() {
        System.out.println("Wybierz opcje");
        for (Option value : Option.values()) {
            System.out.println(value.toString());
        }
    }

    private void addCar() {
        try {
            Car car = dataReader.readAndCreateCar();
            comis.addVehicle(car);
        } catch (InputMismatchException e) {
            printer.printLine("Nie udało się uworzyć auta, niepoprawne dane");

        }
    }

    private void showCars() {
        printer.printCars(comis.getSortedVehicles(Comparator.comparing(Vehicle::getMark, String.CASE_INSENSITIVE_ORDER)));
    }

    private void addMoto() {
        try {
            Motorcycle motorcycle = dataReader.readAndCreateMoto();
            comis.addVehicle(motorcycle);
        } catch (InputMismatchException e) {
            printer.printLine("Nie udało się uworzyć motocyklu, niepoprawne dane");
        }
    }

    private void showMoto() {
        printer.printMoto(comis.getSortedVehicles(Comparator.comparing(Vehicle::getMark, String.CASE_INSENSITIVE_ORDER)));
    }

    private void deleteCar() {
        try {
            Car car = dataReader.readAndCreateCar();
            if (comis.removeVehicle(car))
                printer.printLine("Usunięto samochód");
            else
                printer.printLine("Brak wskazanaego auta");
        } catch (InputMismatchException e) {
            printer.printLine("Nie udało sie utworzyć auta, niepoprawne dane");
        }
    }

    private void deleteMoto() {
        try {
            Motorcycle motorcycle = dataReader.readAndCreateMoto();
            if (comis.removeVehicle(motorcycle))
                printer.printLine("Usunięto motocykl");
            else
                printer.printLine("Brak wskazanaego motocyklu");
        } catch (InputMismatchException e) {
            printer.printLine("Nie udało sie utworzyć motocyklu, niepoprawne dane");
        }
    }

    private void addClient() {
        ComisClient comisClient = dataReader.createComisClient();
        try {
            comis.addClient(comisClient);
        } catch (ClientAlreadyExistException e) {
            printer.printLine(e.getMessage());
        }

    }

    private void showClients() {
        printer.printClients(comis.getSortedClients(Comparator.comparing(Client::getLastName, String.CASE_INSENSITIVE_ORDER)));
    }

    private void searchVehicle() {
        printer.printLine("Podaj numer VIN");
        String vin=dataReader.getString();
        String notFoundVin="Brak pojazdu o takim numerze VIN";
        comis.findVehicleByVin(vin)
                .map(Vehicle::toString)
                .ifPresentOrElse(System.out::println,()-> System.out.println(notFoundVin));
    }
    private void exit() {
        try {
            fileManager.exportData(comis);
            printer.printLine("Eksport danych do pliku zakonczony sukcesem");
        } catch (DataExportException e) {
            printer.printLine(e.getMessage());
        }
        System.out.println("Koniec programu");
        dataReader.close();
    }

    private enum Option {
        EXIT(0, "Wyjście z programu"),
        ADD_CAR(1, "Dodanie samochodu"),
        SHOW_CARS(2, "Wyświetlenie samochodów"),
        ADD_MOTO(3, "Dodanie motoru"),
        SHOW_MOTO(4, "Wyświetlenie motorów"),
        DELETE_CAR(5, "Usunięcie auta"),
        DELETE_MOTO(6, "Usuniecie motoru"),
        ADD_CLIENT(7, "Dodanie klienta"),
        SHOW_CLIENTS(8, "Wyświetlenie klientów"),
        SEARCH_VEHICLE(9,"Wyszukaj pojazd");
        private String description;
        private int option;

        Option(int option, String description) {
            this.description = description;
            this.option = option;
        }

        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("Brak opcji o id " + option);
            }
        }

        @Override
        public String toString() {
            return option + " - " + description;
        }
    }
}
