package pl.mr.carshop.app;

import pl.mr.carshop.exception.NoSuchOptionException;

public enum Option {
    EXIT(0,"Wyjście z programu"),
    ADD_CAR(1,"Dodanie samochodu"),
    SHOW_CARS(2,"Wyświetlenie samochodów"),
    ADD_MOTO(3,"Dodanie motoru"),
    SHOW_MOTO(4,"Wyświetlenie motorów");
    private String description;
    private int option;

    Option(int option,String description) {
        this.description = description;
        this.option = option;
    }

    public static Option createFromInt(int option) throws NoSuchOptionException {
        try {
            return Option.values()[option];
        }catch (ArrayIndexOutOfBoundsException e){
            throw new NoSuchOptionException("Brak opcji o id "+option);
        }
    }

    @Override
    public String toString() {
        return option+" - "+description;
    }
}
