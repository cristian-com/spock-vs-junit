package cristian.com.spock.introduction;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Data
public class Basket {
    private final int id;
    private final Map<Item, Integer> items;

    @Builder
    public Basket(int id) {
        this.id = id;
        this.items = new HashMap<>();
    }

    public void addItem(Item item, int quantity) {
        requireNonNull(item);

        if (item.getCategory() == Category.ELECTRONICS && quantity > 5) {
            throw new IllegalArgumentException("Maximum 5 electronics!");
        }

        items.put(item, quantity);
    }

    public int getItemQuantity(Item item) {
        return items.getOrDefault(item, 0);
    }
}
