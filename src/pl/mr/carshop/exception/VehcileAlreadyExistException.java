package pl.mr.carshop.exception;

public class VehcileAlreadyExistException extends RuntimeException
{
    public VehcileAlreadyExistException(String message) {
        super(message);
    }
}
