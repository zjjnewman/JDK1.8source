package designpatterns.factory.abstractfactory;


import designpatterns.factory.abstractfactory.manufactory.Factory;
import designpatterns.factory.abstractfactory.manufactory.FactoryA;
import designpatterns.factory.abstractfactory.manufactory.FactoryB;
import designpatterns.factory.abstractfactory.product.Product;

public class ProductStore {
    public static void main(String[] args) {
        Factory factoryA = new FactoryA();
        Factory factoryB = new FactoryB();

        Product am = factoryA.manufactureMould();
        Product ac = factoryA.manufactureContainer();

    }
}
