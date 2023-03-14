package ua.prom.roboticsdmc.integerdivision.provider;

import ua.prom.roboticsdmc.integerdivision.domain.DivisionResult;

public interface DivisionViewProvider {

    String provideView(DivisionResult divisionResult);

}
