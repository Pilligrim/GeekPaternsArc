package system_patterns;

import java.util.Collections;
import java.util.List;

public class ListValue<T> {

    private final List<T> list;

    public ListValue(List<T> list) {
        this.list = Collections.unmodifiableList(list);
    }

    public List<T> getList() {
        return list;
    }
}
