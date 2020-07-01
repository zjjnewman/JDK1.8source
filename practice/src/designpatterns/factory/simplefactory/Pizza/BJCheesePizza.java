package designpatterns.factory.simplefactory.Pizza;

public class BJCheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("bjcheese");
        System.out.println(name + " 北京奶酪pizza");
    }
}
