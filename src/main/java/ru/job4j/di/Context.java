package ru.job4j.di;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {
    /**
     * Карта с объектами. В ней мы будем хранить проинициализированные объекты.
     */
    private Map<String, Object> els = new HashMap<String, Object>();

    /**
     * Метод get возвращает проинициализированный объект.
     * @param cl Метод регистрации классов.
     */

    public void reg(Class cl) {
        Constructor[] constructors = cl.getDeclaredConstructors();
        if (constructors.length > 1) {
            throw new IllegalStateException("Class has multiple constructors : " + cl.getCanonicalName());
        }
        Constructor con = constructors[0];
        List<Object> args = new ArrayList<Object>();
        Class[] parameterTypes = con.getParameterTypes();
        for (Class arg : parameterTypes) {
            String canonicalName = arg.getCanonicalName();
            if (!els.containsKey(canonicalName)) {
                throw new IllegalStateException("Object doesn't found in context : " + arg.getCanonicalName());
            }
            args.add(els.get(arg.getCanonicalName()));
        }
        try {
            Object value = con.newInstance(args.toArray());
            els.put(cl.getCanonicalName(), value);
        } catch (Exception e) {
            throw new IllegalStateException("Coun't create an instance of : " + cl.getCanonicalName(), e);
        }
    }

    /**
     * Метод get возвращает проинициализированный объект.
     * @param inst объект добавляемый в Контейнер Context
     */

    public <T> T get(Class<T> inst) {
        return (T) els.get(inst.getCanonicalName());
    }
}