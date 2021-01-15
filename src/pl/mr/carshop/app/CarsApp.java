package pl.mr.carshop.app;

import pl.mr.carshop.exception.NoSuchOptionException;

import java.util.ArrayList;

public class CarsApp {
    public static void main(String[] args) throws NoSuchOptionException {
        final String appNumber="Autokomis v0.5";
        System.out.println(appNumber);
        CarControl carControl=new CarControl();
        carControl.controlLoop();

    }
}
