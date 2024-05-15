import org.example.Interpreter;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class InterpreterTest {
    @Test
    public void testAddition() {
        Interpreter interpreter = new Interpreter();
        HashMap<String, Object> testCases = new HashMap<>();

        // Try different testcases with increasing complexity
        testCases.put("1+1", 2);
        testCases.put("1 + 1    ", 2);
        testCases.put("-1+1", 0);
        testCases.put("5+10", 15);
        testCases.put("-3 +9", 6);

        for (Map.Entry<String, Object> testCase : testCases.entrySet()) {
            assertEquals(testCase.getValue(), interpreter.interpret(testCase.getKey()));
        }

        // Try alphabetic value, expect exception
        try {
            interpreter.interpret("a+1");
            fail("Invalid input exception should be thrown");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Invalid Input: alphabetic values"));
        }

    }
}
