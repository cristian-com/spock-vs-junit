package cristian.com.spock.stubbing;

public class DependencyB implements Dependency {

    @Override
    public String stringMethod() {
        return "value";
    }

    @Override
    public Integer integerMethod(String param) {
        return param.length();
    }

    @Override
    public Boolean booleanMethod(boolean a, String b) {
        return !a;
    }

    @Override
    public void voidMethod(String... parameters) {
    }

}
