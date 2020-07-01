package designpatterns.factory.simplefactory.order;


import designpatterns.factory.simplefactory.Pizza.*;

import java.io.*;

public class OrderPizza {

    public OrderPizza(){
        while (true){
            Pizza pizza = null;
            String orderType;
            orderType = getType();
            if (orderType.equals("bjcheese")){
                pizza = new BJCheesePizza();
            } else if (orderType.equals("bjpepper")){
                pizza = new BJPepperPizza();
            } else if (orderType.equals("ldcheese")) {
                pizza = new LDCheesePizza();
            } else if (orderType.equals("ldpepper")){
                pizza = new LDPepperPizza();
            } else {
                break;
            }
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        }
    }

    public String getType(){
        try {
            System.out.println("要定的pizza ：");
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            String str = strin.readLine();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
