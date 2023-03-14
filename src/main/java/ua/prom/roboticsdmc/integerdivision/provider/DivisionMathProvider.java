package ua.prom.roboticsdmc.integerdivision.provider;

import ua.prom.roboticsdmc.integerdivision.domain.DivisionResult;

public interface DivisionMathProvider {

    DivisionResult provideMathDivision (int division, int divisor);
    
}
