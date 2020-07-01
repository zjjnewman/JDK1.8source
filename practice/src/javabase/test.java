package javabase;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class test {
    Iterator iterator = new ArrayList().iterator();

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        String b = "abcdwer";
        String s = new String("abcdwer");

        System.out.println(System.identityHashCode(b));
        System.out.println(System.identityHashCode(s));

        //反射获取unsafe类
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        long mem = unsafe.allocateMemory(1);
//        System.out.println(unsafe.getAddress(1627674070));
//        System.out.println(unsafe.getAddress(System.identityHashCode(s)));

        StringBuffer sb = new StringBuffer();
        Object o= sb.append('a').append("cdsafddasc1scsacwwwwwwwqweesadaaaaaaaaaaawwwwwwwwwqdsffaasfdfsadafweww").append("dafsafdsafs");
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(o.getClass());
        System.gc();
        System.out.println(sb.capacity());
        System.out.println(sb.length());

        ArrayList arrayList = new ArrayList();
        LinkedList linkedList = new LinkedList();
        HashSet hashSet = new HashSet<>();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.iterator();
        TreeSet treeSet = new TreeSet();

        LinkedHashMap linkedHashMap = new LinkedHashMap();

        HashMap hashMap = new HashMap();
        hashMap.put(null, "dsaf");
        hashMap.put(("null"),"wef");
        System.out.println(hashMap);

    }



}
