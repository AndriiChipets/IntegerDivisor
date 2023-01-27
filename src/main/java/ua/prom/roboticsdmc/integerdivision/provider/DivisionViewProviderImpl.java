package ua.prom.roboticsdmc.integerdivision.provider;

import java.util.ArrayList;
import java.util.List;

import ua.prom.roboticsdmc.integerdivision.domain.DivisionResult;
import ua.prom.roboticsdmc.integerdivision.domain.DivisionStep;

public class DivisionViewProviderImpl implements DivisionViewProvider {

    @Override
    public String provideView(DivisionResult divisionResult) {
        ArrayList<String> viewResult = new ArrayList<>();
        List<DivisionStep> steps = divisionResult.getSteps();
        int divisionLength = calculateNumberLength(divisionResult.getDivision());
        int divisorLength = calculateNumberLength(divisionResult.getDivisor());
        int quotientLength = calculateNumberLength(divisionResult.getQuotient());
        int remainderLength = calculateNumberLength(divisionResult.getRemainder());
        viewResult.add(createFirstColumnLine(divisionResult.getDivision(), divisionResult.getDivisor(), divisionLength, divisorLength));
        viewResult.add(createSecondColumnLine(divisionResult.getDivisor(), divisionLength, divisorLength, quotientLength));
        viewResult.add(createThirdColumnLine(divisionLength, divisorLength, divisionResult.getQuotient()));

        for (int i = 0; i < steps.size(); i++) {
            viewResult.add(createCurrentDivision(steps, i));
            viewResult.add(createCurrentDivisor(steps, i));
            viewResult.add(createHyphensUnderCurrentDivisor(steps, i));
        }
        viewResult.add(createLastColumnLine(divisionLength, divisorLength, remainderLength, divisionResult.getRemainder()));
        return convertLineToColumn(viewResult);
    }

    private String convertLineToColumn(ArrayList<String> lineArrays) {
        StringBuilder viewResultStringBuilder = new StringBuilder();
        for (int i = 0; i < lineArrays.size(); i++) {
            viewResultStringBuilder.append(lineArrays.get(i));
            viewResultStringBuilder.append(System.lineSeparator());
        }
        return String.valueOf(viewResultStringBuilder);
    }

    private int calculateNumberLength(int element) {
        return String.valueOf(element).length();
    }

    private String createFirstColumnLine(int division, int divisor, int divisionLength, int divisorLength) {
        String firstLine;
        if (divisionLength >= divisorLength) {
            firstLine = "_" + division + "|" + divisor;
        } else {
            firstLine = "_" + division + " ".repeat(divisorLength - divisionLength) + "|" + divisor;
        }
        return firstLine;
    }

    private String createSecondColumnLine(int divisor, int divisionLength, int divisorLength, int quotientLength) {
        String secondLine;
        if (divisionLength >= divisorLength) {
            secondLine = " " + divisor + " ".repeat(divisionLength - divisorLength) + "|" + "-".repeat(quotientLength);
        } else {
            secondLine = " " + divisor + "|" + "-".repeat(quotientLength);
        }
        return secondLine;
    }

    private String createThirdColumnLine(int divisionLength, int divisorLength, int quotient) {
        String thirdLine;
        if (divisionLength >= divisorLength) {
            thirdLine = " " + "-".repeat(divisorLength) + " ".repeat(divisionLength - divisorLength) + "|" + quotient;
        } else {
            thirdLine = " " + "-".repeat(divisorLength) + "|" + quotient;
        }
        return thirdLine;
    }

    private String createLastColumnLine(int divisionLength, int divisorLength, int remainderLength, int remainder) {
        String lastLine;
        if (divisionLength >= divisorLength) {
            lastLine = " ".repeat(divisionLength - remainderLength + 1) + remainder;
        } else {
            lastLine = " ".repeat(divisorLength - remainderLength + 1) + remainder;
        }
        return lastLine;
    }

    private String createCurrentDivision(List<DivisionStep> steps, int i) {
        String[] currentStep = (String.valueOf(steps.get(i))).split(" ");
        return " ".repeat(i) + "_" + currentStep[0];
    }

    private String createCurrentDivisor(List<DivisionStep> steps, int i) {
        String[] currentStep = (String.valueOf(steps.get(i))).split(" ");
        return " ".repeat(i + 1) + currentStep[1];
    }

    private String createHyphensUnderCurrentDivisor(List<DivisionStep> steps, int i) {
        String[] currentStep = (String.valueOf(steps.get(i))).split(" ");
        return " ".repeat(i + 1) + "-".repeat(currentStep[0].length());
    }
}
