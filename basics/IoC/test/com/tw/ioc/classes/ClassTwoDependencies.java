package com.tw.ioc.classes;

import com.tw.ioc.Dependencies.Dependency;
import com.tw.ioc.Dependencies.SecondDependency;

import javax.inject.Inject;

public class ClassTwoDependencies {
    private Dependency dependency;
    private SecondDependency secondDependency;

    @Inject
    public ClassTwoDependencies(Dependency dependency, SecondDependency secondDependency) {
        this.dependency = dependency;
        this.secondDependency = secondDependency;
    }

    public String method() {
        return dependency.depMethod() + ", " + secondDependency.depMethod();
    }
}
