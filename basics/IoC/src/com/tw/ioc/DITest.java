package com.tw.ioc;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DITest {

    private Dependency dependency;

    @Test
    public void should_() throws InstantiationException, IllegalAccessException {
        HaoContainer c = new HaoContainer();
        c.registerImplementation(Dependency.class, DependencyImpl.class);

        dependency = (Dependency) c.createInstance(Dependency.class);

        NormalClass normalClass = new NormalClass(dependency);

        assertThat(normalClass.NormalMethod(), is("DI success"));
    }
}
