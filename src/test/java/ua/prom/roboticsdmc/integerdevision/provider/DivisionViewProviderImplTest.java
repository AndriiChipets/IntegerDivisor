package ua.prom.roboticsdmc.integerdevision.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ua.prom.roboticsdmc.integerdivision.domain.DivisionResult;
import ua.prom.roboticsdmc.integerdivision.domain.DivisionStep;
import ua.prom.roboticsdmc.integerdivision.provider.DivisionViewProviderImpl;

class DivisionViewProviderImplTest {

    DivisionViewProviderImpl divisionViewProviderImpl = new DivisionViewProviderImpl();

    @Test
    void provideMathDivision_shouldReturnCorrectExpression_whenDivisionBiggerThenDivisor() {

        List<DivisionStep> steps = new ArrayList<>(Arrays.asList(new DivisionStep(38, 36), new DivisionStep(29, 28),
                new DivisionStep(14, 12), new DivisionStep(25, 24)));
        
        String actual = divisionViewProviderImpl.provideView 
                (DivisionResult.builder()
                        .withSteps(steps)
                        .withDivisor(4)
                        .withDivision(78945)
                        .withQuotient(19736)
                        .withRemainder(1).build());
        String expected = "_78945|4\r\n"
                        + " 4    |-----\r\n"
                        + " -    |19736\r\n"
                        + "_38\r\n"
                        + " 36\r\n"
                        + " --\r\n"
                        + " _29\r\n"
                        + "  28\r\n"
                        + "  --\r\n"
                        + "  _14\r\n"
                        + "   12\r\n"
                        + "   --\r\n"
                        + "   _25\r\n"
                        + "    24\r\n"
                        + "    --\r\n"
                        + "     1\r\n";
                             
        assertEquals(expected, actual);
    }

    @Test
    void provideMathDivision_shouldReturnQuotientEqualsOneAndRemainderEquelsZero_whenInputDivisionEqualsDivisor() {
        List<DivisionStep> steps = new ArrayList<>();
        String actual = divisionViewProviderImpl.provideView(DivisionResult.builder()
                .withSteps(steps)
                .withDivisor(78)
                .withDivision(78)
                .withQuotient(1)
                .withRemainder(0).build());
        String expected = "_78|78\r\n" 
                        + " 78|-\r\n" 
                        + " --|1\r\n" 
                        + "  0\r\n";

        assertEquals(expected, actual);
    }

    @Test
    void provideMathDivision_shouldReturnRamainderAndQuotientEqualsZero_whenInputDivisionIsZero() {
        List<DivisionStep> steps = new ArrayList<>();
        String actual = divisionViewProviderImpl.provideView(DivisionResult.builder()
                .withSteps(steps)
                .withDivisor(7)
                .withDivision(0)
                .withQuotient(0)
                .withRemainder(0).build());
        String expected = "_0|7\r\n" 
                        + " 7|-\r\n" 
                        + " -|0\r\n" 
                        + " 0\r\n";
        
        assertEquals(expected, actual);
    }

    @Test
    void provideMathDivision_shouldReturnRamainderAndQuotientEqualsZero_whenInputDivisorBiggerThenDivision() {
        List<DivisionStep> steps = new ArrayList<>();
        String actual = divisionViewProviderImpl.provideView(DivisionResult.builder()
                .withSteps(steps)
                .withDivisor(124)
                .withDivision(14)
                .withQuotient(0)
                .withRemainder(0).build());
        String expected = "_14 |124\r\n" 
                        + " 124|-\r\n" 
                        + " ---|0\r\n" 
                        + "   0\r\n";
        
        assertEquals(expected, actual);
    }

    @Test
    void provideMathDivision_shouldReturnshouldReturnCorrectExpression_whenInputDivisionMuchBiggerThenDivisor() {
        List<DivisionStep> steps = new ArrayList<>();
        String actual = divisionViewProviderImpl.provideView(DivisionResult.builder()
                .withSteps(steps).withDivisor(5)
                .withDivision(100002)
                .withQuotient(20000)
                .withRemainder(2)
                .build());
        String expected = "_100002|5\r\n" 
                        + " 5     |-----\r\n" 
                        + " -     |20000\r\n" 
                        + "      2\r\n";
        
        assertEquals(expected, actual);
    }
}
