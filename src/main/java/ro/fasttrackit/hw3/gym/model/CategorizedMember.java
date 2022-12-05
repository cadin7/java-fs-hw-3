package ro.fasttrackit.hw3.gym.model;

import lombok.Getter;

@Getter
public class CategorizedMember {

    private final Member member;
    private final String remainingGroup;

    public CategorizedMember(Subscription subscription) {
        this.member = subscription.getMember();
        this.remainingGroup = calculateRemainingTime(subscription.getLeftHours());
    }

    private String calculateRemainingTime(long leftHours) {
        if (leftHours < 10) {
            return "RED";
        } else if (leftHours < 30) {
            return "YELLOW";
        } else {
            return "GREEN";
        }
    }
}
