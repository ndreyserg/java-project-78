package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    @Test
    public void testStringRequired() {
        var s = (new Validator()).string().required();
        assertTrue(s.isValid("hello"));
        assertFalse(s.isValid(""));
        assertFalse(s.isValid(null));
    }

    @Test
    public void testStringMinLength() {
        var s = (new Validator()).string().minLength(5);
        assertTrue(s.isValid("hello"));
        assertTrue(s.isValid("hello world"));
        assertFalse(s.isValid("hell"));
        assertFalse(s.isValid(null));
    }

    @Test
    public void testStringContains() {
        var s = (new Validator()).string();
        assertTrue(s.contains("hello").isValid("hello"));
        assertTrue(s.contains("hello").isValid("hello world"));
        assertTrue(s.contains("world").isValid("hello world"));
        assertFalse(s.contains("world").isValid(""));
        assertFalse(s.contains("world").isValid(null));
        assertFalse(s.contains("world").isValid("hello"));
    }

    @Test
    public void testStringMultiple() {
        var s = (new Validator()).string().required()
                .minLength(10)
                .minLength(5)
                .contains("hell");

        assertTrue(s.isValid("hello"));
        assertFalse(s.isValid("hell"));
    }

    @Test
    public void testNumberRequired() {
        var s = (new Validator().number());
        assertTrue(s.isValid(null));
        s.required();
        assertFalse(s.isValid(null));
        assertTrue(s.isValid(5));
    }

    @Test
    public void testNumberPositive() {
        var s = (new Validator().number());
        assertTrue(s.isValid(-10));
        s.positive();
        assertFalse(s.isValid(-10));
        assertTrue(s.isValid(10));
        assertFalse(s.isValid(0));
    }

    @Test
    public void testNumberRange() {
        var s = (new Validator().number()).range(5, 10);
        assertTrue(s.isValid(10));
        assertTrue(s.isValid(5));
        assertTrue(s.isValid(7));
        assertFalse(s.isValid(4));
        assertFalse(s.isValid(11));
    }

    @Test
    public void testNumberMultiple() {
        var s = (new Validator().number())
                .range(-10, 10)
                .positive();

        assertTrue(s.isValid(1));
        assertTrue(s.isValid(10));
        assertFalse(s.isValid(-1));
        assertFalse(s.isValid(11));
    }

    @Test
    public void testMapRequired() {
        var s = (new Validator().map());
        assertTrue(s.isValid(null));
        s.required();
        assertFalse(s.isValid(null));
        assertTrue(s.isValid(new HashMap<>()));
    }

    @Test
    public void testMapSize() {
        var s = (new Validator().map());
        var map = new HashMap<String, String>();
        map.put("key1", "value1");
        assertTrue(s.isValid(map));
        s.sizeof(2);
        assertFalse(s.isValid(map));
        map.put("key2", "value2");
        assertTrue(s.isValid(map));
        map.put("key3", "value3");
        assertFalse(s.isValid(map));
    }
}
