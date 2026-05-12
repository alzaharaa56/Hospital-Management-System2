package Interface;
import java.util.List;

public interface Searchable<T> {
    List<T> search(String keyword);
    T searchById(String id);
}
