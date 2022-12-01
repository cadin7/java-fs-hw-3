package ro.fasttrackit.hw3.gym.model;

import lombok.ToString;
import ro.fasttrackit.hw3.gym.exception.NotEnoughHoursException;

import java.time.Duration;

import static java.lang.String.format;
import static java.time.Duration.ofHours;

@ToString
public class Subscription {
    private static final String EXCEPTION = "Can't spend more ( %s hours) than remaining hours: %s";

    private final Member member;
    private Duration leftHours;

    public Subscription(Member member, long boughtHours) {
        this.member = member;
        this.leftHours = ofHours(boughtHours);
    }

    public void addTime(long hours) {
        this.leftHours = leftHours.plusHours(hours);
    }

    public void removeTime(long hours) {
        if (this.leftHours.toHours() < hours) {
            throw new NotEnoughHoursException(format(EXCEPTION, hours, this.leftHours.toHours()));
        } else {
            this.leftHours = leftHours.minusHours(hours);
        }
    }

    public Member getMember() {
        return member;
    }

    public long getLeftHours() {
        return leftHours.toHours();
    }
}
