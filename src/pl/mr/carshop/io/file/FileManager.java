package pl.mr.carshop.io.file;

import pl.mr.carshop.model.Comis;

public interface FileManager {
    Comis importData();
    void exportData(Comis comis);
}
