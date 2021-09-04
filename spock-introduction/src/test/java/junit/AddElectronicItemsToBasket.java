package junit;

import cristian.com.spock.introduction.AddItemToBasketUseCase;
import cristian.com.spock.introduction.Basket;
import cristian.com.spock.introduction.BasketRepository;
import cristian.com.spock.introduction.Category;
import cristian.com.spock.introduction.Item;
import cristian.com.spock.introduction.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddElectronicItemsToBasket {

    @Mock ItemRepository itemRepository;
    @Mock BasketRepository basketRepository;
    AddItemToBasketUseCase useCase;

    @BeforeEach
    public void init() {
        useCase = new AddItemToBasketUseCase(basketRepository, itemRepository);
    }

    @Test
    @DisplayName("Add item")
    public void addItem() {
        // Arrange
        var basket = new Basket(1);
        var item = Item.builder()
                .id(1)
                .description("McBook")
                .category(Category.ELECTRONICS)
                .build();
        int quantity = 3;

        when(basketRepository.findBasket(basket.getId())).thenReturn(basket);
        when(itemRepository.findItem(item.getId())).thenReturn(item);
        when(basketRepository.save(basket)).thenAnswer(i -> i.getArguments()[0]);

        // Act
        Basket result = useCase.addItem(basket.getId(), item.getId(), 3);

        // Assert
        assertEquals(result.getItemQuantity(item), quantity);
    }

}
