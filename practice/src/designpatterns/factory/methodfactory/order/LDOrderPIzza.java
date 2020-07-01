package designpatterns.factory.methodfactory.order;

import designpatterns.factory.methodfactory.pizza.LDCheesePizza;
import designpatterns.factory.methodfactory.pizza.LDPepperPizza;
import designpatterns.factory.methodfactory.pizza.Pizza;

public class LDOrderPIzza extends OrderPizza  {
    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if(orderType.equals("cheese")){
            pizza = new LDCheesePizza();
        } else if(orderType.equals("pepper")){
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
