package org.gokareless.examples.graphql;

import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;

import javax.servlet.http.HttpServletRequest;

public class Person {

    private String name;

    public static Person of(String name) {
        Person person = new Person();
        person.name = name;
        return person;
    }

    public String getName(DataFetchingEnvironment environment) {
        GraphQLContext context = environment.getContext();
        HttpServletRequest request = context.getRequest().get();
        boolean encodingEnabled = (Boolean) request.getAttribute("Encoding-enabled");
        return encodingEnabled ? name + " serialized" : name + " not serialized";
    }
}
