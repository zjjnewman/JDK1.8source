package designpatterns.factory.simplefactory.Pizza;

public class LDCheesePizza extends Pizza {

    @Override
    public void prepare() {
        setName("ldcheese");
        System.out.println(name + " 伦敦奶酪pizza");
    }
}
