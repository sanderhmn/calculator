import org.example.Interpreter;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class InterpreterTest {
    @Test
    public void additionShouldSucceed() throws Exception {
        Interpreter interpreter = new Interpreter();

        Map<String, Object> testCases = Map.ofEntries(
                Map.entry("1 + 1", 2.0),
                Map.entry("1 - 1", 0.0),
                Map.entry("1 + -1", 0.0),
                Map.entry("5 + -9", -4.0),
                Map.entry("2 * 4", 8.0),
                Map.entry("6 / 3", 2.0),
                Map.entry("3 - 3 * 2", -3.0),
                Map.entry("3 * 3 - 3", 6.0),
                Map.entry("9 - 5 - 3", 1.0),
                Map.entry("2 + 6 * -1", -4.0),
                Map.entry("9 / 2", 4.5)
        );

        for (Map.Entry<String, Object> testCase : testCases.entrySet()) {
            assertEquals(testCase.getValue(), interpreter.buildTree(testCase.getKey()).getValue());
        }

    }

    @Test
    public void additionExpectException() {
        Interpreter interpreter = new Interpreter();
        Exception thrownException = assertThrows(Exception.class, () -> interpreter.buildTree("a+1"));
        assertEquals("Incorrect format: please enter whitespaces between numbers and operators", thrownException.getMessage());
    }
}
