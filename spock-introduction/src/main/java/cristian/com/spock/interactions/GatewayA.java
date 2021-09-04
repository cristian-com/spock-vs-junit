package cristian.com.spock.interactions;

public class GatewayA implements Gateway {
    @Override
    public void send(String message) {
        System.out.println("Sent " + message + " from A!");
    }
}
