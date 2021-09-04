package junit;

import cristian.com.spock.interactions.GatewayA;
import cristian.com.spock.interactions.GatewayB;
import cristian.com.spock.interactions.InOrderUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
public class Interactions {

    @Mock
    GatewayA gatewayA;
    @Mock
    GatewayB gatewayB;
    InOrderUseCase useCase;

    @BeforeEach
    public void init() {
        useCase = new InOrderUseCase(gatewayA, gatewayB);
    }

    @Test
    void inOrderTest() {
        InOrder inOrder = inOrder(gatewayA, gatewayB);

        useCase.send();

        inOrder.verify(gatewayA).send(any());
        inOrder.verify(gatewayB).send(any());
    }

}
