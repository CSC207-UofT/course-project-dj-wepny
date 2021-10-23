import java.util.List;

public interface API<T> {

    List<T> readFromCSV(String filename);

    Object createItem(List<String> item);
}
