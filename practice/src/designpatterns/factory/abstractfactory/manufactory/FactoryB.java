package designpatterns.factory.abstractfactory.manufactory;
import designpatterns.factory.abstractfactory.product.ContainerProductB;
import designpatterns.factory.abstractfactory.product.MouldProductB;
import designpatterns.factory.abstractfactory.product.Product;

public class FactoryB extends Factory {

    Product c;
    Product m;

    public FactoryB(){
        c = manufactureContainer();
        m = manufactureMould();
        c.show();
        m.show();
    }

    @Override
    public Product manufactureContainer() {
        return new ContainerProductB();
    }

    @Override
    public Product manufactureMould() {
        return new MouldProductB();
    }
}
