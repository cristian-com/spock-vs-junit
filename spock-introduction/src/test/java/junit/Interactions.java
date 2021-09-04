package junit;

import cristian.com.spock.interactions.GatewayA;
import cristian.com.spock.interactions.GatewayB;
import cristian.com.spock.interactions.InteractionUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class Interactions {

    @Mock
    GatewayA gatewayA;
    @Mock
    GatewayB gatewayB;
    InteractionUseCase useCase;

    @BeforeEach
    public void init() {
        useCase = new InteractionUseCase(gatewayA, gatewayB);
    }

    @Test
    void inOrderTest() {
        // Given
        InOrder inOrder = inOrder(gatewayA, gatewayB);
        // When
        useCase.send();
        // Then
        inOrder.verify(gatewayA).send(any());
        inOrder.verify(gatewayB).send(any());
    }

    @Test
    void neverCalls () {
        // When
        useCase.send(3);
        // Then
        verify(gatewayA, never()).send(any());
    }

    @Test
    void exactlyOneCall () {
        // When
        useCase.send(3);
        // Then
        verify(gatewayB, times(1)).send(any());
    }

    @Test
    void betweenNumberOfCalls () {
        // When
        useCase.sendNumberOfTimes(3);
        // Then
        verify(gatewayA, atLeastOnce()).send(any());
        verify(gatewayA, atMost(4)).send(any());
    }

    @Test
    void atLeastNumberOfCalls () {
        // When
        useCase.sendNumberOfTimes(3);
        // Then
        verify(gatewayA, atLeast(2)).send(any());
    }

    @Test
    void atMostNumberOfCalls () {
        // When
        useCase.sendNumberOfTimes(3);
        // Then
        verify(gatewayA, atMost(4)).send(any());
    }
}
