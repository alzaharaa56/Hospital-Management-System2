package Interface;
import java.util.List;

public interface Manageable<T> {
    void add(T entity);
    void remove(String id);
    List<T> getAll();
}
