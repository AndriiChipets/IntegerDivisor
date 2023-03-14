package ua.prom.roboticsdmc.integerdivision.validator;

public class DivisionValidatorImpl implements DivisionValidator {

    @Override
    public void validate(int division, int divisor) {

        if (divisor == 0) {
            throw new ArithmeticException("Divisor can't be 0");
        }
        if (divisor < 0) {
            throw new IllegalArgumentException("Divisor can't be negative");
        }
        if (division < 0) {
            throw new IllegalArgumentException("Division can't be negative");
        }
    }
}
