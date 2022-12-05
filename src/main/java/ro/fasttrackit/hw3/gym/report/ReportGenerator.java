package ro.fasttrackit.hw3.gym.report;

import ro.fasttrackit.hw3.gym.model.CategorizedMember;
import ro.fasttrackit.hw3.gym.model.Member;
import ro.fasttrackit.hw3.gym.model.Subscription;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.*;

public record ReportGenerator(Reader<Subscription> reader,
                              ReportWriter reportWriter) {

    public void generateReport() throws IOException {
        reportWriter.writeReport(new TreeMap<>(groupSubscriptionsByLeftHours()));
    }

    private Map<String, List<Member>> groupSubscriptionsByLeftHours() {
        return reader.read()
                .stream()
                .map(CategorizedMember::new)
                .collect(groupingBy(CategorizedMember::getRemainingGroup,
                        mapping(CategorizedMember::getMember, toList())));
    }
}
