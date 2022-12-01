package ro.fasttrackit.hw3.gym.report;

import ro.fasttrackit.hw3.gym.model.Member;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.joining;

public record ReportWriter() {

    public void writeReport(Map<String, List<Member>> categorizedSubscriptions) throws IOException {
        try (var writer = new BufferedWriter((new FileWriter(getRemainingTimeFileName())))) {
            if (!categorizedSubscriptions.isEmpty()) {
                writeReport(writer, categorizedSubscriptions);
            }
        }
    }

    private void writeReport(BufferedWriter writer, Map<String, List<Member>> categorizedSubscriptions) {
        categorizedSubscriptions.forEach((key, value) ->
                writeLine(writer, key.toUpperCase() + ": " + collectNames(value)));
    }

    private String collectNames(List<Member> value) {
        return value.stream()
                .map(Member::name)
                .collect(joining(", "));
    }

    private void writeLine(BufferedWriter writer, String line) {
        try {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRemainingTimeFileName() {
        return "remaining-time-report-" + now() + ".txt";
    }
}
