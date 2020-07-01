package designpatterns.factory.methodfactory.order;

import designpatterns.factory.methodfactory.pizza.BJCheesePizza;
import designpatterns.factory.methodfactory.pizza.BJPepperPizza;
import designpatterns.factory.methodfactory.pizza.Pizza;

public class BJOrderPizza extends OrderPizza{

    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if(orderType.equals("cheese")){
            pizza = new BJCheesePizza();
        } else if(orderType.equals("pepper")){
            pizza = new BJPepperPizza();
        }
        return pizza;
    }
}
