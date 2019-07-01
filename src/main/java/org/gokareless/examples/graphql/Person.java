package org.gokareless.examples.graphql;

public class Person {

    private String name;

    public static Person of(String name) {
        Person person = new Person();
        person.name = name;
        return person;
    }
}
