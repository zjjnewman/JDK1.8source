package designpatterns.factory.simplefactory.Pizza;

public class LDPepperPizza extends Pizza {

    @Override
    public void prepare() {
        setName("ldpepper");
        System.out.println(name + "伦敦胡椒pizza");
    }
}
