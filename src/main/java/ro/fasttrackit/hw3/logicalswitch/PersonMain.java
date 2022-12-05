package ro.fasttrackit.hw3.logicalswitch;

import java.util.List;

import static java.lang.System.lineSeparator;
import static java.util.List.of;
import static java.util.stream.Collectors.joining;

public class PersonMain {
    private static final String HUNGARY = "Hungary";
    private static final String ROMANIA = "Romania";
    private static final String AMERICA = "Murica";

    public static void main(String[] args) {
        final var personDispatcher = new PersonDispatcher();
        System.out.println(getPersons().stream()
                .map(personDispatcher::dispatch)
                .collect(joining(lineSeparator())));
    }

    private static List<Person> getPersons() {
        return of(
                new Person("Horvath Viktor", 55, HUNGARY),
                new Person("Normal Bela", 24, HUNGARY),
                new Person("Bolond Bela", 13, HUNGARY),
                new Person("Mark Daniel", 9, ROMANIA),
                new Person("Alin Balin", 19, ROMANIA),
                new Person("Sinko Belo", 7, ROMANIA),
                new Person("Mark Budin", 29, AMERICA),
                new Person("Don Kihote", 13, AMERICA));
    }
}
