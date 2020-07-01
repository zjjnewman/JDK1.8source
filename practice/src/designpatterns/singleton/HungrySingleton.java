package designpatterns.singleton;

public class HungrySingleton {
    private final static HungrySingleton singleton = new HungrySingleton();
    private HungrySingleton(){}
    public HungrySingleton getSingleton(){
        return singleton;
    }
}
