package org.gokareless.examples.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.gokareless.examples.graphql.dto.Person;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PersonResolver implements GraphQLQueryResolver {

    public List<Person> getPersons() {
        System.out.println("Executing getPersons()...");
        return Arrays.asList(
                Person.of("<b onmouseover=alert('XSS_exploit_message')>Message</b>"),
                Person.of("David"),
                Person.of("James")
        );
    }

    public Person getPerson(String name) {
        return Person.of("Elvis");
    }

}
