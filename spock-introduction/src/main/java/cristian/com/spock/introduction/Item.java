package cristian.com.spock.introduction;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private final int id;
    private final String description;
    private final Category category;
}
