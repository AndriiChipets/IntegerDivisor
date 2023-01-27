package ua.prom.roboticsdmc.integerdivision;

import ua.prom.roboticsdmc.integerdivision.domain.DivisionResult;
import ua.prom.roboticsdmc.integerdivision.provider.DivisionMathProvider;
import ua.prom.roboticsdmc.integerdivision.provider.DivisionViewProvider;
import ua.prom.roboticsdmc.integerdivision.validator.DivisionValidator;

public class IntegerDivisionCalculator {

    private final DivisionValidator divisionValidator;
    private final DivisionMathProvider divisionMathProvider;
    private final DivisionViewProvider divisionViewProvider;

    public IntegerDivisionCalculator(DivisionValidator divisionValidator, DivisionMathProvider divisionMathProvider,
            DivisionViewProvider divisionViewProvider) {
        this.divisionValidator = divisionValidator;
        this.divisionMathProvider = divisionMathProvider;
        this.divisionViewProvider = divisionViewProvider;
    }

    public String calculateDivision(int division, int divisor) {
        divisionValidator.validate(division, divisor);
        DivisionResult divisionResult = divisionMathProvider.provideMathDivision(division, divisor);
        return divisionViewProvider.provideView(divisionResult);
    }
}
