package ua.prom.roboticsdmc.integerdivision;

import ua.prom.roboticsdmc.integerdivision.provider.DivisionMathProvider;
import ua.prom.roboticsdmc.integerdivision.provider.DivisionMathProviderImpl;
import ua.prom.roboticsdmc.integerdivision.provider.DivisionViewProvider;
import ua.prom.roboticsdmc.integerdivision.provider.DivisionViewProviderImpl;
import ua.prom.roboticsdmc.integerdivision.validator.DivisionValidator;
import ua.prom.roboticsdmc.integerdivision.validator.DivisionValidatorImpl;

import java.util.Scanner;

public class IntegerDivisionConsoleApplication {
    public static void main(String[] args) {

        DivisionMathProvider divisionMathProvider = new DivisionMathProviderImpl();
        DivisionValidator divisionValidator = new DivisionValidatorImpl();
        DivisionViewProvider divisionViewProvider = new DivisionViewProviderImpl();

        try (Scanner scanner = new Scanner(System.in)) {
            IntegerDivisionCalculator integerDivisionCalculator = new IntegerDivisionCalculator(divisionValidator,
                    divisionMathProvider, divisionViewProvider);

            System.out.println("Input division: ");
            int division = scanner.nextInt();

            System.out.println("Input divisor: ");
            int divisor = scanner.nextInt();

            String result = integerDivisionCalculator.calculateDivision(division, divisor);

            System.out.println("Result: ");
            System.out.println(result);
        }
    }
}
