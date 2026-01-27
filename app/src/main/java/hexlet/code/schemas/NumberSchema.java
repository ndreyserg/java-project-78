package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        this.addRule("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        this.addRule("positive", n -> Objects.nonNull(n) && n > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.addRule("range", n -> Objects.nonNull(n) && n >= min && n <= max);
        return this;
    }
}
