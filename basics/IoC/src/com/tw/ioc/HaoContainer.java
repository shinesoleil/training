package com.tw.ioc;

import java.util.HashMap;
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

    public Object createInstance(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        return classMap.get(clazz).newInstance()    ;
    }
}
