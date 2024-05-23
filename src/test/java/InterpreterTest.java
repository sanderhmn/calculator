import org.example.Interpreter;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class InterpreterTest {
    @Test
    public void additionShouldSucceed() {
        Interpreter interpreter = new Interpreter();
        HashMap<String, Object> testCases = new HashMap<>();

        // Try different testcases with increasing complexity
        testCases.put("1+1", 2);
        testCases.put("1 + 1    ", 2);
        testCases.put("-1+1", 0);
        testCases.put("5+10", 15);
        testCases.put("-3 +9", 6);
        testCases.put("5+-9", -4);

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
