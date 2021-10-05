package cristian.com.spock.stubbing;

public interface Dependency {

    String stringMethod();

    Integer integerMethod(String param);

    Boolean booleanMethod(boolean a, String b);

    void voidMethod(String... parameters);

    default void exceptionMethod() {
        throw new RuntimeException();
    }

    default String objectMethod(Object param) {
        return param.getClass().getName();
    }

}
