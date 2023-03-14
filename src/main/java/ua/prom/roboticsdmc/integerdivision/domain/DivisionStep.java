package ua.prom.roboticsdmc.integerdivision.domain;

import java.util.Objects;

public class DivisionStep {
    private final int currentDivision;
    private final int currentDivisor;

    public DivisionStep(int currentDivision, int currentDivisor) {
        this.currentDivision = currentDivision;
        this.currentDivisor = currentDivisor;
    }

    public int getCurrentDivision() {
        return currentDivision;
    }

    public int getCurrentDivisor() {
        return currentDivisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DivisionStep divisionStep = (DivisionStep) o;
        return Objects.equals(currentDivision, divisionStep.currentDivision)
                && Objects.deepEquals(currentDivisor, divisionStep.currentDivisor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentDivision, currentDivisor);
    }

    @Override
    public String toString() {
        return String.valueOf(currentDivision) + " " + currentDivisor;
    }
}
