package hexlet.code.schemas;

import java.util.HashMap;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    private final HashMap<String, Predicate<T>> rules = new HashMap<>();

    public final boolean isValid(T t) {
        return this.rules.values().stream().allMatch(p -> p.test(t));
    }

    protected final void addRule(String ruleName, Predicate<T> rule) {
        rules.put(ruleName, rule);
    }
}
