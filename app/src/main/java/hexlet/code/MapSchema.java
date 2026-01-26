package hexlet.code;

import java.util.Map;
import java.util.Objects;

public class MapSchema<T> extends BaseSchema<Map<?, T>> {
    public MapSchema<T> required() {
        rules.put("required", Objects::nonNull);
        return this;
    }

    public MapSchema<T> sizeof(int size) {
        rules.put("sizeof", m -> m.size() == size);
        return this;
    }

    public MapSchema<T> shape(Map<?, BaseSchema<T>> shape) {
        rules.put("shape", m -> shape.entrySet()
                .stream()
                .allMatch((entry) -> {
                    var value = m.get(entry.getKey());
                    return entry.getValue().isValid(value);
                }));
        return this;
    }
}
