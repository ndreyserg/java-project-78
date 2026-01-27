package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        final int minLength = 5;
        var s = (new Validator()).string().minLength(minLength);
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
        final int minLengthFirst = 10;
        final int minLengthSecond = 5;
        var s = (new Validator()).string().required()
                .minLength(minLengthFirst)
                .minLength(minLengthSecond)
                .contains("hell");

        assertTrue(s.isValid("hello"));
        assertFalse(s.isValid("hell"));
    }

    @Test
    public void testNumberRequired() {
        final int testValue = 5;
        var s = (new Validator().number());
        assertTrue(s.isValid(null));
        s.required();
        assertFalse(s.isValid(null));
        assertTrue(s.isValid(testValue));
    }

    @Test
    public void testNumberPositive() {
        final int negativeValue = -10;
        final int positiveValue = 10;
        var s = (new Validator().number());
        assertTrue(s.isValid(negativeValue));
        s.positive();
        assertFalse(s.isValid(negativeValue));
        assertTrue(s.isValid(positiveValue));
        assertFalse(s.isValid(0));
    }

    @Test
    public void testNumberRange() {
        final int rangeStart = 5;
        final int rangeEnd = 10;
        final int inRange = 7;
        final int outOfRangeLeft = 4;
        final int outOfRangeRight = 11;
        var s = (new Validator().number()).range(rangeStart, rangeEnd);
        assertTrue(s.isValid(rangeEnd));
        assertTrue(s.isValid(rangeStart));
        assertTrue(s.isValid(inRange));
        assertFalse(s.isValid(outOfRangeLeft));
        assertFalse(s.isValid(outOfRangeRight));
    }

    @Test
    public void testNumberMultiple() {
        final int rangeStart = -10;
        final int rangeEnd = 10;

        var s = (new Validator().number())
                .range(rangeStart, rangeEnd)
                .positive();

        assertTrue(s.isValid(1));
        assertFalse(s.isValid(rangeStart));
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
        var s = (new Validator()).map();
        HashMap<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        assertTrue(s.isValid(map));
        s.sizeof(2);
        assertFalse(s.isValid(map));
        map.put("key2", "value2");
        assertTrue(s.isValid(map));
        map.put("key3", "value3");
        assertFalse(s.isValid(map));
    }

    @Test
    public void testMapShape() {
        var v = new Validator();
        var s = v.map();
        var schemas = new HashMap<String, BaseSchema<String>>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        s.shape(schemas);

        var human1 = new HashMap<String, String>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(s.isValid(human1));

        var human2 = new HashMap<String, String>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(s.isValid(human2));

        var human3 = new HashMap<String, String>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(s.isValid(human3));

    }
}
