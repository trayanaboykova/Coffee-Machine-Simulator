import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testcase.TestCase;

import java.util.List;

public class CoffeeMachineTest extends StageTest<String> {
    @Override
    public List<TestCase<String>> generate() {
        return List.of(
                new TestCase<String>()
                        .setInput("")
                        .setAttach("Starting to make a coffee\n" +
                                "Grinding coffee beans\n" +
                                "Boiling water\n" +
                                "Mixing boiled water with crushed coffee beans\n" +
                                "Pouring coffee into the cup\n" +
                                "Pouring some milk into the cup\n" +
                                "Coffee is ready!")
        );
    }

    @Override
    public CheckResult check(String reply, String clue) {
        String[] expectedPhrases = {
                "Starting to make a coffee",
                "Grinding coffee beans",
                "Boiling water",
                "Mixing boiled water with crushed coffee beans",
                "Pouring coffee into the cup",
                "Pouring some milk into the cup",
                "Coffee is ready!"
        };

        String[] actualPhrases = reply.trim().split("\n");

        if (expectedPhrases.length != actualPhrases.length) {
            return CheckResult.wrong(
                    "Expected " + expectedPhrases.length + " phrases but got " + actualPhrases.length
            );
        }

        for (int i = 0; i < expectedPhrases.length; i++) {
            if (!actualPhrases[i].trim().toLowerCase().equals(expectedPhrases[i].trim().toLowerCase())) {
                return CheckResult.wrong(
                        "Expected: \"" + expectedPhrases[i] + "\", but got: \"" + actualPhrases[i] + "\".\n" +
                                "The phrase \"" + expectedPhrases[i] + "\" is either missing or is not in the correct order, in the output."
                );
            }
        }

        return CheckResult.correct();
    }
}