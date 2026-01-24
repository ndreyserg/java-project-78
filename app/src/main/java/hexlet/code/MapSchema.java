package hexlet.code;



import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema required() {
        rules.put("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        rules.put("sizeof", m -> m.size() == size);
        return this;
    }
}
