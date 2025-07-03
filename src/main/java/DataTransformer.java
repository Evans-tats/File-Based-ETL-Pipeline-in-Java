import java.util.List;
import java.util.stream.Collectors;

public class DataTransformer {
    public List<DataRecord> cleanData(List<DataRecord> records) {
        return records.stream()
                .filter(r -> r.getEmail() != null && !r.getEmail().isEmpty())
                .filter(r -> r.getAge() > 0)
                .collect(Collectors.toList());

    }
}