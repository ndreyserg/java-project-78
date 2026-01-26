package hexlet.code;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        this.addRule("required", s -> s != null && !s.isEmpty());
        return this;
    }

    public StringSchema minLength(int min) {
        this.addRule("minLength", s -> s != null && s.length() >= min);
        return this;
    }

    public StringSchema contains(String substring) {
        this.addRule("contains", s -> s != null && s.contains(substring));
        return this;
    }
}
