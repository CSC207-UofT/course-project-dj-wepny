import java.util.List;

public interface API {

    Object readFromCSV(String filename);

    Object createItem(List<String> item);
}
