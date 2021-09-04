package introduction

import cristian.com.spock.introduction.Basket
import cristian.com.spock.introduction.Category
import cristian.com.spock.introduction.Item
import spock.lang.Specification

class AddItemToBasketSpec extends Specification {

    def "Add item to a basket limited to 5 electronics."() {
        given:
        def basket = new Basket(1)

        def item = Item.builder()
                .id(1)
                .description("Corona")
                .category(Category.ELECTRONICS)
                .build()

        when: "The item is added to the basket"
        basket.addItem(item, 10)

        then: "A illegal argument exception should be thrown"
        def exception = thrown(IllegalArgumentException)

        and: "The message should warn about the item limit"
        exception.message == "Maximum 5 electronics!"
    }

}
