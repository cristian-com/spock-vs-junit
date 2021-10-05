package cristian.com.spock.stubbing;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StubbingUseCase implements Dependency {

    private final DependencyA delegateA;
    private final DependencyB delegateB;

    @Override
    public String stringMethod() {
        return delegateA.stringMethod();
    }

    @Override
    public Integer integerMethod(String param) {
        return delegateA.integerMethod(param);
    }

    @Override
    public Boolean booleanMethod(boolean a, String b) {
        return delegateB.booleanMethod(a, b);
    }

    @Override
    public void voidMethod(String... parameters) {
        delegateB.voidMethod(parameters);
    }

    @Override
    public void exceptionMethod() {
        delegateA.exceptionMethod();
    }

    @Override
    public String objectMethod(Object param) {
        return delegateB.objectMethod(param);
    }

}
