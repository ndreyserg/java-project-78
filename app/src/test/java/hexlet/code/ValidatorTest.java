package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTest {

    @Test
    public void testStringRequired() {
        var s = (new Validator()).string().required();
        assertEquals(true, s.isValid("hello"));
        assertEquals(false, s.isValid(""));
        assertEquals(false, s.isValid(null));
    }

    @Test
    public void testStringMinLength() {
        var s = (new Validator()).string().minLength(5);
        assertEquals(true, s.isValid("hello"));
        assertEquals(true, s.isValid("hello world"));
        assertEquals(false, s.isValid("hell"));
        assertEquals(false, s.isValid(null));
    }

    @Test
    public void testStringContains() {
        var s = (new Validator()).string();
        assertEquals(true, s.contains("hello").isValid("hello"));
        assertEquals(true, s.contains("hello").isValid("hello world"));
        assertEquals(true, s.contains("world").isValid("hello world"));
        assertEquals(false, s.contains("world").isValid(""));
        assertEquals(false, s.contains("world").isValid(null));
        assertEquals(false, s.contains("world").isValid("hello"));
    }

    @Test
    public void testStringMultiRules() {
        var s = (new Validator()).string().required()
                .minLength(10)
                .minLength(5)
                .contains("hell");

        assertEquals(true, s.isValid("hello"));
        assertEquals(false, s.isValid("hell"));
    }
}
