package hexlet.code;

import java.util.HashMap;
import java.util.function.Predicate;

public class StringSchema {

    private final HashMap<String, Predicate<String>> rules;

    public StringSchema() {
        rules = new HashMap<>();
    }

    public StringSchema required() {
        rules.put("required", (s) -> s != null && !s.isEmpty());
        return this;
    }

    public StringSchema minLength(int min) {
        rules.put("minLength", (s) -> s != null && s.length() >= min);
        return this;
    }

    public StringSchema contains(String substring) {
        rules.put("contains", (s) -> s != null && s.contains(substring));
        return this;
    }

    public Boolean isValid(String string) {
        return this.rules.values().stream().allMatch(p -> p.test(string));
    }
}
