package pac.testcase.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class ObjectSizeUtils {

    private static Instrumentation inst = null;

    public static void premain(String agentArgs, Instrumentation inst) {
        ObjectSizeUtils.inst = inst;
    }

    public static long sizeOf(Object o) throws IllegalAccessException {
    if(inst == null) throw new IllegalStateException("not initialized yet");
        SizeCounter sizeCounter = new SizeCounter();
        sizeOf(o, sizeCounter);
        return sizeCounter.getSize();
    }

    public static void sizeOf(Object o,SizeCounter sizeCounter) throws IllegalAccessException {
        if(o!=null){
            sizeCounter.addSize(inst.getObjectSize(o));
            Class c = o.getClass();
            Field[] fields = c.getDeclaredFields();
            for (Field f : fields) {
                int mod = f.getModifiers();

                if(Modifier.isStatic(mod) || f.getType().isPrimitive()){
                    continue;
                }
                f.setAccessible(true);
                Object o1 = f.get(o);
                sizeOf(o1,sizeCounter);
            }
        }

    }

    private static void sizeOfArray(Object o, SizeCounter sizeCounter) {
        if(o.getClass().isArray()){
            int len = Array.getLength(o);
            sizeCounter.addSize(inst.getObjectSize(o));
            for (int i = 0; i < len; i++) {
                Object o1 = Array.get(o, i);
                if (o.getClass().isArray()) {
                    sizeOfArray(o1, sizeCounter);
                }
            }
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        //fucking Boot-Class-Path:
        Person person = new Person("Alvez","make me death before you make me old");
        Field[] fields= person.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
        }
    }

    @Test
    public void testSizeUtils() throws IllegalAccessException {
        Person person = new Person("Alvez","make me death before you make me old");
        person.setBunxin(new Person("Alvez_Dark","die like a man"));
    }

    static final Logger logger = LoggerFactory.getLogger(ObjectSizeUtils.class);
}

class SizeCounter{
        private long size;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void addSize(long size){
        this.setSize(this.getSize() + size);
    }
}

class Person{
    private  String name;
    private String life;
    private Person bunxin;

    public Person getBunxin() {
        return bunxin;
    }

    public void setBunxin(Person bunxin) {
        this.bunxin = bunxin;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getLife() {return life;}
    public void setLife(String life) {this.life = life;}

    Person(String name, String life) {
        this.name = name;
        this.life = life;
    }
}