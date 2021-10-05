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
import static org.mockito.ArgumentMatchers.isA;
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
        // Given
        int order = 0;
        List<Object> input = List.of(DUMMY_BOOLEAN, DUMMY_STRING, DUMMY_INT, DUMMY_LIST, DUMMY_SET, DUMMY_MAP);
        when(dependencyB.objectMethod(anyBoolean())).thenReturn("" + order++);
        when(dependencyB.objectMethod(anyString())).thenReturn("" + order++);
        when(dependencyB.objectMethod(anyInt())).thenReturn("" + order++);
        when(dependencyB.objectMethod(anyList())).thenReturn("" + order++);
        when(dependencyB.objectMethod(anySet())).thenReturn("" + order++);
        when(dependencyB.objectMethod(anyMap())).thenReturn("" + order);

        for (int index = 0; index < input.size(); index++) {
            // When
            String response = useCase.objectMethod(input.get(index));
            // Then
            assertEquals(response, "" + index);
        }
    }
}
