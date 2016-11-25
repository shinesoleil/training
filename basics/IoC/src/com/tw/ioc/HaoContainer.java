package com.tw.ioc;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class HaoContainer {
    Map<Class, Class> classMap;

    public HaoContainer() {
        this.classMap = new HashMap<>();
    }

    public void registerImplementation(Class<?> clazz) {
        registerImplementation(clazz, clazz);
    }

    public void registerImplementation(Class<?> clazz, Class<?> clazzImpl) {
        classMap.put(clazz, clazzImpl);
    }

    public Object createInstance(Class<?> clazz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        List<Constructor> constructors = Arrays.asList(clazz.getConstructors());
        Optional<Constructor> constructorOptional = Optional.empty();

        for (Constructor cons : constructors) {
            if (cons.getAnnotation(Inject.class) != null) {
                constructorOptional = Optional.of(cons);
                break;
            }
        }

        if (constructors.size() == 0 || !constructorOptional.isPresent()) {
            return classMap.get(clazz).newInstance();
        }

        Inject injectAnnotation = (Inject) constructors.get(0).getAnnotation(Inject.class);

        Constructor constructor = constructorOptional.get();
        List<Class> paramTypes = Arrays.asList(constructor.getParameterTypes());
        List<Object> objects = new ArrayList<>();

        for (Class c : paramTypes) {
            objects.add(this.createInstance(c));
        }

        return constructor.newInstance(objects.toArray());
    }
}
