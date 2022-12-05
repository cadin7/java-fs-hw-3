package ro.fasttrackit.hw3.gym.report;

import ro.fasttrackit.hw3.gym.model.Member;
import ro.fasttrackit.hw3.gym.model.Subscription;

import java.util.List;

import static java.time.LocalDate.of;

public class InMemorySubscriptionReader implements Reader<Subscription> {
    @Override
    public List<Subscription> read() {
        return List.of(
                new Subscription(new Member(1L, "Arny Schwarzy", of(1947, 7, 30)), 60),
                new Subscription(new Member(2L, "A Stone", of(1972, 5, 2)), 30),
                new Subscription(new Member(3L, "Kati Bela", of(1979, 5, 4)), 29),
                new Subscription(new Member(4L, "Big Peter", of(1994, 1, 4)), 69),
                new Subscription(new Member(5L, "Bruno", of(1988, 1, 22)), 21),
                new Subscription(new Member(6L, "Gizell", of(1973, 2, 4)), 9),
                new Subscription(new Member(7L, "Laza", of(1978, 4, 12)), 5));
    }
}
