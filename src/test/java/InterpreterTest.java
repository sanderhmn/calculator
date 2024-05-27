import org.example.Interpreter;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class InterpreterTest {
    @Test
    public void additionShouldSucceed() {
        Interpreter interpreter = new Interpreter();

        Map<String, Object> testCases = Map.of(
                "1 + 1", 2,
                "-1 + 1",0,
                "-3 + 9", 6,
                "5 + -9", -4,
                "5 - 3", 2);

        for (Map.Entry<String, Object> testCase : testCases.entrySet()) {
            assertEquals(testCase.getValue(), interpreter.interpret(testCase.getKey()));
        }
    }

    @Test
    public void additionExpectException() {
        Interpreter interpreter = new Interpreter();

        // Try alphabetic value, expect exception
        try {
            interpreter.interpret("a+1");
            fail("Invalid input: exception should be thrown");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Invalid Input: non-numeric values"));
        }
    }
}
