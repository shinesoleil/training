package com.tw.ioc.Dependencies;

public class SecondDependencyImpl implements SecondDependency{
    @Override
    public String depMethod() {
        return "Second DI success";
    }
}
