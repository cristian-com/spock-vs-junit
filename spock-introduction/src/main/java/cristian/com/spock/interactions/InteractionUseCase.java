package cristian.com.spock.interactions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InteractionUseCase {

    private final GatewayA  gatewayA;
    private final GatewayB gatewayB;

    public void send() {
        gatewayA.send("One");
        gatewayB.send("Two");
    }

    public void send(int option) {
        if (option % 2 == 0) {
            gatewayA.send("One");
        } else {
            gatewayB.send("Two");
        }
    }

    public void sendNumberOfTimes(int times) {
        for (int i = 0; i < times; i++) {
            gatewayA.send("One");
        }
    }

}
