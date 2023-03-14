package ua.prom.roboticsdmc.integerdivision.domain;

import java.util.List;
import java.util.Objects;

public class DivisionResult {

    private final List<DivisionStep> steps;
    private final int divisor;
    private final int division;
    private final int quotient;
    private final int remainder;

    private DivisionResult(Builder builder) {
        this.steps = builder.steps;
        this.divisor = builder.divisor;
        this.division = builder.division;
        this.quotient = builder.quotient;
        this.remainder = builder.remainder;
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<DivisionStep> getSteps() {
        return steps;
    }

    public int getDivisor() {
        return divisor;
    }

    public int getDivision() {
        return division;
    }

    public int getQuotient() {
        return quotient;
    }

    public int getRemainder() {
        return remainder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DivisionResult divisionResult = (DivisionResult) o;
        return Objects.equals(steps, divisionResult.steps) 
                && Objects.equals(divisor, divisionResult.divisor)
                && Objects.equals(division, divisionResult.division)
                && Objects.equals(quotient, divisionResult.quotient)
                && Objects.equals(remainder, divisionResult.remainder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(steps, divisor, division, quotient, remainder);
    }

    @Override
    public String toString() {
        return "DivisionResult [steps=" + steps + ", divisor=" + divisor + ", division=" + division + ", quotient="
                + quotient + ", remainder=" + remainder + "]";
    }

    public static class Builder {

        private List<DivisionStep> steps;
        private int divisor;
        private int division;
        private int quotient;
        private int remainder;

        private Builder() {
        }

        public Builder withSteps(List<DivisionStep> steps) {
            this.steps = steps;
            return this;
        }

        public Builder withDivisor(int divisor) {
            this.divisor = divisor;
            return this;
        }

        public Builder withDivision(int division) {
            this.division = division;
            return this;
        }

        public Builder withQuotient(int quotient) {
            this.quotient = quotient;
            return this;
        }

        public Builder withRemainder(int remainder) {
            this.remainder = remainder;
            return this;
        }

        public DivisionResult build() {
            return new DivisionResult(this);
        }
    }
}
