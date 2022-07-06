package com.company.InterfacesAndAbstraction.Lab.P04SayHelloExtended;

public abstract class BasePerson implements Person {
    private String name;

    protected BasePerson(String name) {
       this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
