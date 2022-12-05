package ro.fasttrackit.hw3.gym.model;

import java.time.LocalDate;

public record Member(long id, String name, LocalDate birthdate) {
}
