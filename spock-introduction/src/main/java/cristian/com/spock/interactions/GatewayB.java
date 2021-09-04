package cristian.com.spock.interactions;

public class GatewayB implements Gateway {
    @Override
    public void send(String message) {
        System.out.println("Sent " + message + " from B!");
    }
}
