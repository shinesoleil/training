package com.tw.ioc;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DITest {

    private Dependency dependency;
    private NormalClass normalClass;

    @Test
    public void should_instantiate_registered_class() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        HaoContainer c = new HaoContainer();
        c.registerImplementation(Dependency.class, DependencyImpl.class);

        dependency = (Dependency) c.createInstance(Dependency.class);

        NormalClass normalClass = new NormalClass(dependency);

        assertThat(normalClass.NormalMethod(), is("DI success"));
    }

    @Test
    public void should_instantiate_registered_class_with_dependency() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        HaoContainer c = new HaoContainer();
        c.registerImplementation(Dependency.class, DependencyImpl.class);
        c.registerImplementation(NormalClass.class);

        normalClass = (NormalClass) c.createInstance(NormalClass.class);

        assertThat(normalClass.NormalMethod(), is("DI success"));
    }
}
