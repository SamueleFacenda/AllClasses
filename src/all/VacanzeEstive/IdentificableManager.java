package all.VacanzeEstive;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class IdentificableManager<T extends Identificable & Clonable2<T>> {
    private final ArrayList<T> list;
    public IdentificableManager(T[] array) {
        list = new ArrayList<>(Arrays.asList(array));
    }
    public void add(T id) {
        if(list.stream().noneMatch(id::haveSameId))
            try {
                list.add(id.clone2());
            }catch(CloneNotSupportedException e){}
    }
    public T get(Object id) {
        return list.stream()
                .filter(a -> a.haveThisID(id))
                .findAny()
                .orElse(null);
    }
    public Stream<T> stream() {
        return list.stream();
    }
    public Stream<T> parallelStream() {
        return list.parallelStream();
    }
}
