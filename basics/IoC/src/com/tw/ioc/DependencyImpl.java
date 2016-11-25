package com.tw.ioc;

public class DependencyImpl implements Dependency {
    @Override
    public String depMethod() {
        return "DI success";
    }
}
