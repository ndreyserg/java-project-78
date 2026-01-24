package hexlet.code;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        rules.put("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        rules.put("positive", n -> n > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        rules.put("range", n -> n >= min && n <= max);
        return this;
    }
}
