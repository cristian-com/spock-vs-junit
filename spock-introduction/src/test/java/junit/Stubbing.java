package junit;

import cristian.com.spock.stubbing.Dependency;
import cristian.com.spock.stubbing.DependencyA;
import cristian.com.spock.stubbing.DependencyB;
import cristian.com.spock.stubbing.StubbingUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Stubbing {

    @Mock
    DependencyA dependencyA;
    @Mock
    DependencyB dependencyB;
    StubbingUseCase useCase;

    static final String DUMMY_STRING = "Hello";
    static final Integer DUMMY_INT = 1;
    static final Boolean DUMMY_BOOLEAN = true;
    static final List<Object> DUMMY_LIST = Collections.emptyList();
    static final Set<Object> DUMMY_SET = Collections.emptySet();
    static final Map<Object, Object> DUMMY_MAP = Collections.emptyMap();

    @BeforeEach
    public void init() {
        useCase = new StubbingUseCase(dependencyA, dependencyB);
    }

    @Test
    void whenAnyInput() {
        // Given
        int expectedResult = DUMMY_INT;
        when(dependencyA.integerMethod(any())).thenReturn(expectedResult);
        // When
        int response = useCase.integerMethod(DUMMY_STRING);
        // Then
        assertEquals(response, expectedResult);
    }

    @Test
    void whenAnyInstanceOf() {
        // Given
        DependencyA input = new DependencyA();
        String expectedResult = DUMMY_STRING;
        when(dependencyB.objectMethod(isA(Dependency.class))).thenReturn(expectedResult); // Behaves the same as any(Clazz.class)
        // When
        String response = useCase.objectMethod(input);
        // Then
        assertEquals(response, expectedResult);
    }

    @Test
    void whenAnyOfType() {
        // Given
        DependencyA input = new DependencyA();
        String expectedResult = DUMMY_STRING;
        when(dependencyB.objectMethod(any(DependencyA.class))).thenReturn(expectedResult);
        // When
        String response = useCase.objectMethod(input);
        // Then
        assertEquals(response, expectedResult);
    }

    @Test
    void whenAnyCommonClasses() {
        Map<Object, String> input = Map.of(DUMMY_BOOLEAN, "boolean",
                DUMMY_STRING, "string",
                DUMMY_INT, "int",
                DUMMY_LIST, "list",
                DUMMY_SET, "set",
                DUMMY_MAP, "map");

        when(dependencyB.objectMethod(anyBoolean())).thenReturn(input.get(DUMMY_BOOLEAN));
        when(dependencyB.objectMethod(anyString())).thenReturn(input.get(DUMMY_STRING));
        when(dependencyB.objectMethod(anyInt())).thenReturn(input.get(DUMMY_INT));
        when(dependencyB.objectMethod(anyList())).thenReturn(input.get(DUMMY_LIST));
        when(dependencyB.objectMethod(anySet())).thenReturn(input.get(DUMMY_SET));
        when(dependencyB.objectMethod(anyMap())).thenReturn(input.get(DUMMY_MAP));

        assertEquals(input.get(DUMMY_BOOLEAN), useCase.objectMethod(DUMMY_BOOLEAN));
        assertEquals(input.get(DUMMY_STRING), useCase.objectMethod(DUMMY_STRING));
        assertEquals(input.get(DUMMY_INT), useCase.objectMethod(DUMMY_INT));
        assertEquals(input.get(DUMMY_LIST), useCase.objectMethod(DUMMY_LIST));
        assertEquals(input.get(DUMMY_SET), useCase.objectMethod(DUMMY_SET));
        assertEquals(input.get(DUMMY_MAP), useCase.objectMethod(DUMMY_MAP));
    }

    @Test
    void whenIsEqual() {
        // Given
        String expectedResult = DUMMY_INT.getClass().getName();
        when(dependencyB.objectMethod(eq(DUMMY_STRING))).thenReturn(expectedResult);
        // When
        String response = useCase.objectMethod(DUMMY_STRING);
        // Then
        assertEquals(response, expectedResult);
    }

    @Test
    void whenIsNotNull() {
        // Given
        String input = DUMMY_STRING;
        String expectedResult = DUMMY_INT.getClass().getName();
        when(dependencyB.objectMethod(isNotNull())).thenReturn(expectedResult);
        // When
        String response = useCase.objectMethod(input);
        // Then
        assertEquals(response, expectedResult);
    }

    @Test
    void whenIsNull() {
        // Given
        String input = null;
        String expectedResult = DUMMY_INT.getClass().getName();
        when(dependencyB.objectMethod(isNull())).thenReturn(expectedResult);
        // When
        String response = useCase.objectMethod(input);
        // Then
        assertEquals(response, expectedResult);
    }

}
