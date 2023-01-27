package ua.prom.roboticsdmc.integerdivision.provider;

import java.util.ArrayList;
import java.util.List;

import ua.prom.roboticsdmc.integerdivision.domain.DivisionResult;
import ua.prom.roboticsdmc.integerdivision.domain.DivisionStep;

public class DivisionMathProviderImpl implements DivisionMathProvider {

    @Override
    public DivisionResult provideMathDivision(int division, int divisor) {

        int currentDivision;
        int currentDivisor;
        int remainder;
        int stepCounter = 0;
        boolean isAddedNullToQuotient = false;
        int quotient = 0;
        List<DivisionStep> steps = new ArrayList<>();

        String divisionString = String.valueOf(division);
        char[] divisionChars = divisionString.toCharArray();
        currentDivision = Integer.parseInt(String.valueOf(divisionChars[0]));

        int i = 0;
        while (i < divisionChars.length) {
            if (stepCounter < 1) {
                i++;
            }
            while (currentDivision < divisor && i < divisionChars.length) {
                currentDivision = Integer.parseInt(String.valueOf(currentDivision) + divisionChars[i]);
                i++;
                if (isAddedNullToQuotient) {
                    quotient = Integer.parseInt(String.valueOf(quotient) + 0);
                }
                isAddedNullToQuotient = true;
            }
            stepCounter++;
            currentDivisor = currentDivision / divisor * divisor;
            if (stepCounter > 1 && currentDivisor != 0) {
                steps.add(new DivisionStep(currentDivision, currentDivisor));
            }
            remainder = currentDivision % divisor;
            quotient = Integer.parseInt(String.valueOf(quotient) + currentDivision / divisor);
            currentDivision = remainder;
            isAddedNullToQuotient = false;
        }
        if (division == 0||divisor > division) {
            remainder = 0;
        } else {
            remainder = currentDivision % divisor;
        }
        return DivisionResult.builder()
                .withSteps(steps)
                .withDivisor(divisor)
                .withDivision(division)
                .withQuotient(quotient)
                .withRemainder(remainder).build();
    }
}
