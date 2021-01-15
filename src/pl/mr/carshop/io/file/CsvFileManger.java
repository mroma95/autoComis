package pl.mr.carshop.io.file;

import pl.mr.carshop.exception.DataExportException;
import pl.mr.carshop.exception.DataImportException;
import pl.mr.carshop.exception.InvalidDataException;
import pl.mr.carshop.model.*;

import java.io.*;
import java.util.Collection;

public class CsvFileManger implements FileManager {
    private static final String VEHICLE_FILE_NAME = "Comis.csv";
    private static final String CLIENTS_FILE_NAME = "Comis_clients.csv";

    @Override
    public void exportData(Comis comis) {
        exportVehicles(comis);
        exportClients(comis);
    }

    private void exportClients(Comis comis) {
        Collection<ComisClient> clients = comis.getClient().values();
        exportToCsv(clients, CLIENTS_FILE_NAME);
    }

    private void exportVehicles(Comis comis) {
        Collection<Vehicle> vehicles = comis.getVehicles().values();
        exportToCsv(vehicles, VEHICLE_FILE_NAME);
    }

    private <T extends CsvConvertable> void exportToCsv(Collection<T> collection, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (T element : collection) {
                bufferedWriter.write(element.toCsv());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu danych do pliku " + fileName);
        }
    }


    @Override
    public Comis importData() {
        Comis comis = new Comis();
        importVehicles(comis);
        importUsers(comis);
        return comis;
    }

    private void importUsers(Comis comis) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CLIENTS_FILE_NAME))) {
            bufferedReader.lines()
                    .map(this::createUserFromString)
                    .forEach(comis::addClient);
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku " + CLIENTS_FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odcyztu pliku "+CLIENTS_FILE_NAME);
        }
    }

    private void importVehicles(Comis comis) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(VEHICLE_FILE_NAME))) {
            bufferedReader.lines()
                    .map(this::createObjectFromString)
                    .forEach(comis::addVehicle);
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku " + VEHICLE_FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku "+VEHICLE_FILE_NAME);
        }
    }


    private ComisClient createUserFromString(String csvText) {
        String[] split = csvText.split(";");
        return new ComisClient(split[0], split[1], split[2]);
    }

    private Vehicle createObjectFromString(String csvText) {
        String[] split = csvText.split(";");
        String type = split[0];
        if (Car.TYPE.equals(type)) {
            return createCar(split);
        } else if (Motorcycle.TYPE.equals(type)) {
            return createMoto(split);
        }
        throw new InvalidDataException("Nieznany typ publikacji: " + type);
    }

    private Vehicle createMoto(String[] split) {
        String mark = split[1];
        String model = split[2];
        String vinNumber = split[3];
        int year = Integer.parseInt(split[4]);
        int wheelNumbers = Integer.parseInt(split[5]);
        int numberOfSeats = Integer.parseInt(split[6]);
        String motoFrame = split[7];
        return new Motorcycle(mark, model, vinNumber, year, wheelNumbers, numberOfSeats, motoFrame);
    }

    private Vehicle createCar(String[] split) {
        String mark = split[1];
        String model = split[2];
        String vinNumber = split[3];
        int year = Integer.parseInt(split[4]);
        int wheelNumbers = Integer.parseInt(split[5]);
        int numberOfSeats = Integer.parseInt(split[6]);
        String engineType = split[7];
        String colour = split[8];
        double engineCapacity = Double.parseDouble(split[9]);
        double price = Double.parseDouble(split[10]);
        return new Car(mark, model, vinNumber, year, wheelNumbers, numberOfSeats, engineType, colour, engineCapacity, price);
    }
}

