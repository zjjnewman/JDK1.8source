package designpatterns.factory.abstractfactory.manufactory;


import designpatterns.factory.abstractfactory.product.Product;

public abstract class Factory {
    public abstract Product manufactureContainer();
    public abstract Product manufactureMould();
}
