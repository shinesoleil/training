package com.tw.ioc;

import javax.inject.Inject;

public class NormalClass {
    private Dependency dependency;

    @Inject
    public NormalClass(Dependency dependency) {
        this.dependency = dependency;
    }

    String NormalMethod() {
        return dependency.depMethod();
    }
}
