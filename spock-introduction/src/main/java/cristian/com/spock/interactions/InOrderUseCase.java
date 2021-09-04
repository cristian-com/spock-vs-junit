package cristian.com.spock.interactions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InOrderUseCase {

    private final GatewayA  gatewayA;
    private final GatewayB gatewayB;

    public void send() {
        gatewayA.send("One");
        gatewayB.send("Two");
    }

}
