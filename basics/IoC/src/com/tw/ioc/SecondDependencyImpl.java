package com.tw.ioc;

public class SecondDependencyImpl implements SecondDependency{
    @Override
    public String depMethod() {
        return "Second DI success";
    }
}
