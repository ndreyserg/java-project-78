package hexlet.code;

public class Validator {
    public StringSchema string() {
        return new StringSchema();
    }

    public NumberSchema number() {
        return new NumberSchema();
    }

    public MapSchema<String> map() {
        return new MapSchema<>();
    }
}
