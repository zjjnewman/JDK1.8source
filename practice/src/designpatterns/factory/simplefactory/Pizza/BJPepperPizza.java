package designpatterns.factory.simplefactory.Pizza;

public class BJPepperPizza extends Pizza {
    @Override
    public void prepare() {
        setName("bjpepper");
        System.out.println(name + "北京胡椒pizza");
    }
}
