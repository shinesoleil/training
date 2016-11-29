package com.tw.ioc.classes;

import com.tw.ioc.Dependencies.Dependency;

import javax.inject.Inject;

public class NormalClass {
    private Dependency dependency;

    @Inject
    public NormalClass(Dependency dependency) {
        this.dependency = dependency;
    }

    public String NormalMethod() {
        return dependency.depMethod();
    }
}
