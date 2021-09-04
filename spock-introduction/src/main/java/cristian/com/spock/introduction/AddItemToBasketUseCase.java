package cristian.com.spock.introduction;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddItemToBasketUseCase {

    private final BasketRepository basketRepository;
    private final ItemRepository itemRepository;

    public Basket addItem(int basketId, int itemId, int quantity) {
        Basket basket = basketRepository.findBasket(basketId);
        Item item = itemRepository.findItem(itemId);

        basket.addItem(item, quantity);

        return basketRepository.save(basket);
    }

}
