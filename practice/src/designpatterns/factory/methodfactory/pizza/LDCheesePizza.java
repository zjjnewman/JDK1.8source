package designpatterns.factory.methodfactory.pizza;

public class LDCheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("伦敦奶酪pizza");
        System.out.println("伦敦奶酪pizza 准备原材料");
    }
}
