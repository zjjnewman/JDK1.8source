package designpatterns.singleton;

public class DCLLazySingleton {

    private volatile static DCLLazySingleton dclLazySingleton;

    private DCLLazySingleton(){}

    public DCLLazySingleton getDclLazySingleton(){

        if(dclLazySingleton == null){
            synchronized(DCLLazySingleton.class){
                if(dclLazySingleton == null){
                    dclLazySingleton = new DCLLazySingleton();
                }
            }
        }
        return dclLazySingleton;
    }
}
class Main1{
    public static void main(String[] args) {

    }
}