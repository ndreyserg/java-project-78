package hexlet.code;

import java.util.Map;
import java.util.Objects;

public final class MapSchema<T> extends BaseSchema<Map<?, T>> {
    public MapSchema<T> required() {
        this.addRule("required", Objects::nonNull);
        return this;
    }

    public MapSchema<T> sizeof(int size) {
        this.addRule("sizeof", m -> m.size() == size);
        return this;
    }

    public MapSchema<T> shape(Map<?, BaseSchema<T>> shape) {
        this.addRule("shape", m -> shape.entrySet()
                .stream()
                .allMatch((entry) -> {
                    var value = m.get(entry.getKey());
                    return entry.getValue().isValid(value);
                }));
        return this;
    }
}
