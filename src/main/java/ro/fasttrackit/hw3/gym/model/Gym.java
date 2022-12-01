package ro.fasttrackit.hw3.gym.model;

import ro.fasttrackit.hw3.gym.exception.NoMemberFoundException;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.Set;

import static java.time.LocalDate.now;
import static java.time.Period.between;
import static java.util.Optional.ofNullable;

public record Gym(Set<Subscription> subscriptions) {
    public Gym(Set<Subscription> subscriptions) {
        this.subscriptions = ofNullable(subscriptions)
                .map(HashSet::new)
                .orElseGet(HashSet::new);
    }

    public void timeSpent(Member member, long spentHours) {
        final var sub = getSubscriptionByMember(member);

        sub.ifPresent(subscription -> subscription.removeTime(spentHours));
    }

    public void timePurchased(Member member, long purchasedHours) {
        final var sub = getSubscriptionByMember(member);

        sub.ifPresent(subscription -> subscription.addTime(purchasedHours));
    }

    public long getTotalRemainingHours() {
        return subscriptions.stream()
                .mapToLong(Subscription::getLeftHours)
                .sum();
    }

    public double getAverageAge() {
        return getAgeStatistics().getAverage();
    }

    public int getMinimumAge() {
        return getAgeStatistics().getMin();
    }

    public int getMaximumAge() {
        return getAgeStatistics().getMax();
    }

    public String getMemberInfo(String name) {
        return subscriptions.stream()
                .filter(subscription -> subscription.getMember().name().contains(name))
                .map(Subscription::toString)
                .findFirst()
                .orElseThrow(() -> new NoMemberFoundException("No member was found with name: " + name));
    }

    private Optional<Subscription> getSubscriptionByMember(Member member) {
        return subscriptions.stream()
                .filter(subscription -> subscription.getMember().id() == member.id())
                .findFirst();
    }

    private IntSummaryStatistics getAgeStatistics() {
        return subscriptions.stream()
                .map(Subscription::getMember)
                .map(Member::birthdate)
                .mapToInt(this::getYearsFromNow)
                .summaryStatistics();
    }

    private int getYearsFromNow(LocalDate birthdate) {
        return between(birthdate, now()).getYears();
    }
}
