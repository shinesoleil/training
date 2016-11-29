package com.tw.ioc.Dependencies;

public class DependencyImpl implements Dependency {
    @Override
    public String depMethod() {
        return "DI success";
    }
}
