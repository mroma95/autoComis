package pl.mr.carshop.io.file;

import pl.mr.carshop.exception.DataExportException;
import pl.mr.carshop.exception.DataImportException;
import pl.mr.carshop.model.Comis;

import java.io.*;

public class SerializableFileManager implements FileManager {
    private static final String VEHCILES_FILE_NAME = "Comis.c";
    private static final String CLIENTS_FILE_NAME = "Comis_clients.c";

    @Override
    public void exportData(Comis comis) {
        exportVehciles(comis);
        exportClients(comis);
    }

    private void exportVehciles(Comis comis) {
        try (FileOutputStream fos = new FileOutputStream(VEHCILES_FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(comis);
        } catch (FileNotFoundException e) {
            throw new DataExportException("Brak pliku " + VEHCILES_FILE_NAME);
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu danych do pliku " + VEHCILES_FILE_NAME);
        }
    }

    private void exportClients(Comis comis) {
        try (FileOutputStream fos = new FileOutputStream(CLIENTS_FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(comis);
        } catch (FileNotFoundException e) {
            throw new DataExportException("Brak pliku " + CLIENTS_FILE_NAME);
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu danych do pliku " + CLIENTS_FILE_NAME);
        }
    }

    @Override
    public Comis importData() {
        Comis comis=new Comis();
        importVehicles();
        importClients();
        return comis;

    }

    private Comis importClients() {
        try (FileInputStream fis = new FileInputStream(CLIENTS_FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            return (Comis) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku " + CLIENTS_FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku " + CLIENTS_FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Niezgodny typ danych w pliku " + CLIENTS_FILE_NAME);
        }
    }

    private Comis importVehicles() {
        try (FileInputStream fis = new FileInputStream(VEHCILES_FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            return (Comis) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku " + VEHCILES_FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku " + VEHCILES_FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Niezgodny typ danych w pliku " + VEHCILES_FILE_NAME);
        }
    }
}
