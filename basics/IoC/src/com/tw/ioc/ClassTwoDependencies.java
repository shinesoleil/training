package com.tw.ioc;

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
