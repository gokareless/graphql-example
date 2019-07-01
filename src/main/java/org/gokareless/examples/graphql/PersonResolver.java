package org.gokareless.examples.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PersonResolver implements GraphQLQueryResolver {

    public List<Person> getPersons(DataFetchingEnvironment environment) {
        GraphQLContext context = environment.getContext();
        Device device = (Device) context.getRequest().get().getAttribute("Device");
        if (device.isMobile()) {
            System.out.println("Executing for mobile device...");
        } else {
            System.out.println("Executing for desktop device...");
        }
        return Arrays.asList(
                Person.of("Mike"),
                Person.of("David"),
                Person.of("James")
        );
    }

    public Person getPerson(String name) {
        return Person.of("Elvis");
    }

}
