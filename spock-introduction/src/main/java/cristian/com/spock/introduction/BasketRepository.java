package cristian.com.spock.introduction;

public interface BasketRepository {

    Basket findBasket(int id);

    Basket save(Basket basket);

}
