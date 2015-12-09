package cn.liborange.example.learning;

import org.apache.commons.beanutils.BeanUtils;
import org.omg.CORBA._PolicyStub;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liborange on 15/10/2.
 */
public class Person {
    private String Name ="";

    private String Email ="";

    private int age;
    public static String print(){
        return  " what me";
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        Person person = new Person();
        Map<String,String> map = new HashMap<>();
        map.put("Name","herry");
        map.put("Email", "qq.com");
        map.put("Age", "10");
        Object obj = new Person();
        BeanUtils.populate(obj,map);
        person = (Person)obj;
        System.out.println(person.getName()+"\t"+person.getAge());

        person.setName("tom");

        person.setAge(21);

        try {

//克隆，仅此一行而已！

            Person person2 =  (Person) BeanUtils.cloneBean(person);

            System.out.println(person2.getName()+">>"+person2.getAge());

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (InvocationTargetException e) {

            e.printStackTrace();

        } catch (NoSuchMethodException e) {

            e.printStackTrace();

        }

    }



    public String getName() {

        return Name;

    }


    public void setName(String name) {

        this.Name = name;

    }


    public String getEmail() {

        return Email;

    }


    public void setEmail(String email) {

        this.Email = email;

    }


    public int getAge() {

        return age;

    }


    public void setAge(int age) {

        this.age = age;

    }


}

