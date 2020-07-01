package designpatterns.factory.abstractfactory.product;

public class ContainerProductB extends ContainerProduct {
    @Override
    public void show() {
        System.out.println("container B was made");
    }
}
