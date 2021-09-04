package spock

import cristian.com.spock.introduction.AddItemToBasketUseCase
import cristian.com.spock.introduction.Basket
import cristian.com.spock.introduction.BasketRepository
import cristian.com.spock.introduction.Category
import cristian.com.spock.introduction.Item
import cristian.com.spock.introduction.ItemRepository
import spock.lang.Specification

class AddElectronicItemsToBasketSpec extends Specification {

    ItemRepository itemRepository = Mock()
    BasketRepository basketRepository = Mock()
    AddItemToBasketUseCase useCase = new AddItemToBasketUseCase(basketRepository, itemRepository)

    def "Add item" () {
        given:
        var basket = new Basket(1);
        var item = Item.builder()
                .id(1)
                .description("McBook")
                .category(Category.ELECTRONICS)
                .build();
        int quantity = 3;
        itemRepository.findItem(item.id) >> item
        basketRepository.findBasket(basket.id) >> basket
        basketRepository.save(basket) >> { Basket it -> return it }

        when:
        def response = useCase.addItem(basket.id, item.id, quantity)

        then:
        assert response.getItemQuantity(item) == quantity
    }

}
