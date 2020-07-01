package designpatterns.factory.methodfactory.pizza;

public class BJPepperPizza extends Pizza{
    @Override
    public void prepare() {
        setName("北京胡椒pizza");
        System.out.println("北京胡椒pizza 准备原材料");
    }
}
