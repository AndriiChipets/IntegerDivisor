package ua.prom.roboticsdmc.integerdivision;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ua.prom.roboticsdmc.integerdivision.domain.DivisionResult;
import ua.prom.roboticsdmc.integerdivision.domain.DivisionStep;
import ua.prom.roboticsdmc.integerdivision.provider.DivisionMathProvider;
import ua.prom.roboticsdmc.integerdivision.provider.DivisionViewProvider;
import ua.prom.roboticsdmc.integerdivision.validator.DivisionValidator;

@ExtendWith(MockitoExtension.class)
class IntegerDivisionCalculatorTest {

    @Mock 
    DivisionValidator divisionValidator;
    
    @Mock 
    DivisionMathProvider divisionMathProvider;
    
    @Mock 
    DivisionViewProvider divisionViewProvider;
    
    @InjectMocks 
    IntegerDivisionCalculator actual;
      
    @Test
    void provideMathDivision_shouldReturnCorrectExpression_whenDivisionBiggerThenDivisor() {
        
        String expected =  "_78945|4\r\n"
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
        List<DivisionStep> steps = new ArrayList<>(Arrays.asList(new DivisionStep(38, 36), new DivisionStep(29, 28),
                new DivisionStep(14, 12), new DivisionStep(25, 24)));
        
        doNothing().when(divisionValidator).validate(78945, 4);
        when(divisionMathProvider.provideMathDivision (78945,4)).thenReturn(DivisionResult.builder()
                .withSteps(steps)
                .withDivisor(4)
                .withDivision(78945)
                .withQuotient(19736)
                .withRemainder(1).build());  
        when(divisionViewProvider.provideView(DivisionResult.builder()
                .withSteps(steps)
                .withDivisor(4)
                .withDivision(78945)
                .withQuotient(19736)
                .withRemainder(1).build()))
        .thenReturn(expected);

        assertEquals(expected, actual.calculateDivision(78945, 4));
        
        InOrder inOrder = inOrder(divisionValidator, divisionMathProvider, divisionViewProvider);
        
        inOrder.verify(divisionValidator).validate(78945, 4);
        inOrder.verify(divisionMathProvider).provideMathDivision (78945,4);
        inOrder.verify(divisionViewProvider).provideView(DivisionResult.builder()
                .withSteps(steps)
                .withDivisor(4)
                .withDivision(78945)
                .withQuotient(19736)
                .withRemainder(1).build());
    }
    
    @Test
    void calculateDivision_shouldReturnArithmeticException_whenDivisorIsNull() {
        doThrow(new ArithmeticException("Divisor can't be 0"))
        .when(divisionValidator)
        .validate(100, 0);
        assertThrows(ArithmeticException.class, () -> actual.calculateDivision(100, 0));
    }

    @Test
    void calculateDivision_shouldReturnIllegalArgumentException_whenDivisorIsNegative() {
        doThrow(new IllegalArgumentException("Divisor can't be negative"))
        .when(divisionValidator)
        .validate(1, -1);
        assertThrows(IllegalArgumentException.class, () -> actual.calculateDivision(1, -1));
    }

    @Test
    void calculateDivision_shouldReturnIllegalArgumentException_whenDivisionIsNegative() {
        doThrow(new IllegalArgumentException("Division can't be negative"))
        .when(divisionValidator)
        .validate(-1, 1);
        assertThrows(IllegalArgumentException.class, () -> actual.calculateDivision(-1, 1));
    }
}
