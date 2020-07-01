package designpatterns.factory.abstractfactory.product;

public class ContainerProductA extends ContainerProduct {
    @Override
    public void show() {
        System.out.println("container A was made");
    }
}
