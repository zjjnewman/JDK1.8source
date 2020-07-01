package designpatterns.singleton;

public class LazySingleton {
    private static LazySingleton lazySingleton;
    private LazySingleton(){}
    public LazySingleton getLazySIngleton() {
        if(lazySingleton == null){
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }

}

class Main{
    public static void main(String[] args) {
    }
}
