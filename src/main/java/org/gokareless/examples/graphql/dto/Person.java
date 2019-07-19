package org.gokareless.examples.graphql.dto;

import org.gokareless.examples.graphql.aspects.Encoded;

public class Person {

    @Encoded
    private String name;

    public static Person of(String name) {
        Person person = new Person();
        person.name = name;
        return person;
    }

    public String getName() {
        return name;
    }
//
//    public String name(DataFetchingEnvironment environment) {
//        GraphQLContext context = environment.getContext();
//        HttpServletRequest request = context.getRequest().get();
//        boolean encodingEnabled = (Boolean) request.getAttribute("Encoding-enabled");
//        return encodingEnabled ? name + " serialized" : name + " not serialized";
//    }
}
