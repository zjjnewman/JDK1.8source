package javabase;

import java.io.*;

public class StreamTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person p = new Person();
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream("PersonSerialized"));
            oos.writeObject(p);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            oos.close();
        }


        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("/Users/jin/workspace/java/javasource/PersonSerialized"));
//            Person o = (Person) ois.readObject();
            Object o = ois.readObject();
            System.out.println(o);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ois.close();
        }
        System.out.println(new String[3][][][][][].getClass());

    }
}

class Person implements Serializable {

    public static final long serialVersionUID = 123243546L;

    private String str = "流对象序列化反序列化测试";

    private String name;
    private String age;


    public void setStr(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public String getAge() {
        return age;
    }

}

