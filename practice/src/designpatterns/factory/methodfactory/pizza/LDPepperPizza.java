package designpatterns.factory.methodfactory.pizza;

public class LDPepperPizza extends Pizza {
    @Override
    public void prepare() {
        setName("伦敦胡椒pizza");
        System.out.println("伦敦胡椒pizza 准备原材料");
    }
}
