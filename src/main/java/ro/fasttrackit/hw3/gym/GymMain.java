package ro.fasttrackit.hw3.gym;

import ro.fasttrackit.hw3.gym.model.Gym;
import ro.fasttrackit.hw3.gym.model.Member;
import ro.fasttrackit.hw3.gym.model.Subscription;
import ro.fasttrackit.hw3.gym.report.InMemorySubscriptionReader;
import ro.fasttrackit.hw3.gym.report.ReportGenerator;
import ro.fasttrackit.hw3.gym.report.ReportWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

public class GymMain {

    public static void main(String[] args) throws IOException {
        new ReportGenerator(new InMemorySubscriptionReader(), new ReportWriter()).generateReport();

        printOperations();
    }

    private static void printOperations() {
        final var arnySchwarzy = new Member(1L, "Arny Schwarzy", LocalDate.of(1947, 7, 30));
        final var stony = new Member(2L, "A Stone", LocalDate.of(1972, 5, 2));
        final var beluci = new Member(3L, "Kati Bela", LocalDate.of(1979, 5, 4));

        final var arnySub = new Subscription(arnySchwarzy,15);
        final var stonySub = new Subscription(stony, 20);
        final var belaSub = new Subscription(beluci, 5);

        final var gym = new Gym(Set.of(arnySub, stonySub, belaSub));

        getMembersInfo(gym);

        System.out.println("The MIN age is: " + gym.getMinimumAge());
        System.out.println("The AVG age is: " + gym.getAverageAge());
        System.out.println("The MAX age is: " + gym.getMaximumAge());

        System.out.println("Total remaining hours: " + gym.getTotalRemainingHours());

        gym.timePurchased(arnySchwarzy, 10);
        gym.timeSpent(stony, 15);

        System.out.println("Total remaining hours: " + gym.getTotalRemainingHours());

        getMembersInfo(gym);
    }

    private static void getMembersInfo(Gym gym) {
        System.out.println(gym.getMemberInfo("Arny"));
        System.out.println(gym.getMemberInfo("Stone"));
        System.out.println(gym.getMemberInfo("Kati"));
    }
}
