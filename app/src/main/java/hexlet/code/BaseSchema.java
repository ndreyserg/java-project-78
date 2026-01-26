package hexlet.code;

import java.util.HashMap;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected final HashMap<String, Predicate<T>> rules = new HashMap<>();

    public boolean isValid(T t) {
        return this.rules.values().stream().allMatch(p -> p.test(t));
    }
}
