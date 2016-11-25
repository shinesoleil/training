package com.tw.ioc;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        if (constructors.size() == 0) {
            return classMap.get(clazz).newInstance();
        }

        Annotation annotation = constructors.get(0).getAnnotation(Inject.class);

        if (annotation != null) {
            Dependency dependency = (Dependency) this.createInstance(Dependency.class);
            return classMap.get(clazz).getConstructor(Dependency.class).newInstance(dependency);
        } else {
            return classMap.get(clazz).newInstance();
        }
    }
}
