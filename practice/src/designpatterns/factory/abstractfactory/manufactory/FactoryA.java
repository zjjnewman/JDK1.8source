package designpatterns.factory.abstractfactory.manufactory;


import designpatterns.factory.abstractfactory.product.ContainerProductA;
import designpatterns.factory.abstractfactory.product.MouldProductA;
import designpatterns.factory.abstractfactory.product.Product;

public class FactoryA extends Factory {
    public Product c;
    public Product m;

    public FactoryA(){
        c = manufactureContainer();
        m = manufactureMould();
    }

    @Override
    public Product manufactureContainer() {
        return new ContainerProductA();
    }

    @Override
    public Product manufactureMould() {
        return new MouldProductA();
    }
}
