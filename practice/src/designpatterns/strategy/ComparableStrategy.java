package designpatterns.strategy;

import java.util.Comparator;

public class ComparableStrategy<T> {
    public static void main(String[] args) {


        Cat[] cats= {new Cat(1), new Cat(2)};
        ComparableStrategy<Cat> comparableStrategy = new ComparableStrategy<Cat>();
        comparableStrategy.sort1(cats);

        Comparator<Cat> catComparator = new Comparator<Cat>() {
            @Override
            public int compare(Cat o1, Cat o2) {
                return 0;
            }
        };
        comparableStrategy.sort2(cats, catComparator);
    }



    public void sort1(Comparable<T>[] arr){

    }

    public void sort2(T[] arr, Comparator<T> comparator){

    }
}

class Cat implements Comparable<Cat>{
    int weight;
    public Cat(int weight){
        this.weight = weight;
    }

    @Override
    public int compareTo(Cat o) {
        if(this.weight > o.weight){
            return 1;
        }
        if(this.weight < o.weight){
            return -1;
        }
        return 0;
    }
}