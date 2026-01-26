package hexlet.code;

public final class Validator {
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
