package ro.fasttrackit.hw3.logicalswitch;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

public class PersonDispatcher {
    private final Map<Predicate<Person>, Function<Person, String>> logicalSwitch = new HashMap<>();

    public PersonDispatcher() {
        logicalSwitch.put(person -> person.name().contains("Mark"), this::printMark);
        logicalSwitch.put(person -> person.age() < 18, this::printMinor);
        logicalSwitch.put(person -> person.address().contains("Hungary"), this::printFromHungary);
        logicalSwitch.put(person -> person.age() > 18 && person.address().contains("Romania"), this::printAdultFromRomania);
    }

    public String dispatch(Person person) {
        return logicalSwitch.entrySet()
                .stream()
                .filter(entry -> entry.getKey().test(person))
                .map(entry -> entry.getValue().apply(person))
                .collect(joining(lineSeparator()));
    }

    private String printMark(Person person) {
        return "Ohh, hi " + person.name() + "!";
    }

    private String printMinor(Person person) {
        return person.name() + " is minor!";
    }

    private String printFromHungary(Person person) {
        return person.name() + " is from Hungary!";
    }

    private String printAdultFromRomania(Person person) {
        return person.name() + " is " + person.age() + " years old from Romania!";
    }
}
