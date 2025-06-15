import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testcase.TestCase;
import org.hyperskill.hstest.testing.TestedProgram;

import java.util.ArrayList;
import java.util.List;


class TestClue {
    int water;
    int milk;
    int coffeeBeans;
    int cups;
    int money;

    String feedback;
    int remainingCount;

    TestClue(int water, int milk, int coffeeBeans, int cups, int money, String feedback, int remainingCount) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cups = cups;
        this.money = money;
        this.feedback = feedback;
        this.remainingCount = remainingCount;
    }
}

public class CoffeeMachineTest extends StageTest<TestClue> {
    @Override
    public List<TestCase<TestClue>> generate() {
        return List.of(
            new TestCase<TestClue>()
                .setAttach(new TestClue(
                3000,
                3000,
                3000 ,
                3000,
                0,
                "This test checks \"fill\" action",
                2))
                .setInput(
                    "remaining\n" +
                    "fill\n" +
                    "3000\n" +
                    "3000\n" +
                    "3000\n" +
                    "3000\n" +
                    "remaining\n" +
                    "exit\n"),

            new TestCase<TestClue>()
                .setAttach(new TestClue(
                -250,
                0,
                -16 ,
                -1,
                4, "This test checks \"buy\" action for a cup of espresso",
                2))
                .setInput(
                    "remaining\n" +
                    "buy\n" +
                    "1\n" +
                    "remaining\n" +
                    "exit\n"),

            new TestCase<TestClue>()
                .setAttach(new TestClue(
                -350,
                -75,
                -20 ,
                -1,
                7, "This test checks \"buy\" action for a cup of latte",
                2))
                .setInput(
                    "remaining\n" +
                    "buy\n" +
                    "2\n" +
                    "remaining\n" +
                    "exit\n"),

            new TestCase<TestClue>()
                .setAttach(new TestClue(
                -200,
                -100,
                -12 ,
                -1,
                6, "This test checks \"buy\" action for a cup of cappuccino",
                2))
                .setInput(
                    "remaining\n" +
                    "buy\n" +
                    "3\n" +
                    "remaining\n" +
                    "exit\n"),

            new TestCase<TestClue>()
                .setAttach(new TestClue(
                0,
                0,
                0 ,
                0,
                -550, "This test checks \"take\" action",
                2))
                .setInput(
                    "remaining\n" +
                    "take\n" +
                    "remaining\n" +
                    "exit\n"),

            new TestCase<TestClue>()
                .setAttach(new TestClue(
                0,
                0,
                0 ,
                0,
                0, "This test checks \"back\" action, right after \"buy\" action",
                2))
                .setInput(
                    "remaining\n" +
                    "buy\n" +
                    "back\n" +
                    "remaining\n" +
                    "exit\n"),

                new TestCase<TestClue>()
                    .setAttach(new TestClue(
                    700 - 400,
                    390 - 540,
                    80 - 120,
                    7 - 9,
                    0 - 550,
                    "This test is exactly like in the example - try to run it by yourself",
                    5))
                    .setInput(
                        "remaining\n" +
                        "buy\n" +
                        "2\n" +
                        "remaining\n" +
                        "buy\n" +
                        "2\n" +
                        "fill\n" +
                        "1000\n" +
                        "0\n" +
                        "0\n" +
                        "0\n" +
                        "remaining\n" +
                        "buy\n" +
                        "2\n" +
                        "remaining\n" +
                        "take\n" +
                        "remaining\n" +
                        "exit\n")
        );
    }

    @Override
    public CheckResult check(String reply, TestClue clue) {
        String[] lines = reply.trim().split("\\n");

        // Ensure the program is printing something.
        if (lines.length <= 1) {
            return CheckResult.wrong("Looks like you didn't print anything!");
        }

        int expectedWaterDiff = clue.water;
        int expectedMilkDiff = clue.milk;
        int expectedCoffeeBeansDiff = clue.coffeeBeans;
        int expectedCupsDiff = clue.cups;
        int expectedMoneyDiff = clue.money;

        String feedback = clue.feedback;
        int remainingCount = clue.remainingCount;

        List<Integer> milk = new ArrayList<>();
        List<Integer> water = new ArrayList<>();
        List<Integer> coffeeBeans = new ArrayList<>();
        List<Integer> cups = new ArrayList<>();
        List<Integer> money = new ArrayList<>();

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] words = line.trim().split("\\s+");

            // Check if the line contains information about resources.
            String firstWord = words[0].replace("$", "");

            int amount;
            try {
                amount = Integer.parseInt(firstWord);
            } catch (Exception e) {
                continue;
            }

            if (line.contains("milk")) {
                milk.add(amount);
            } else if (line.contains("water")) {
                water.add(amount);
            } else if (line.contains("beans")) {
                coffeeBeans.add(amount);
            } else if (line.contains("cups")) {
                cups.add(amount);
            } else if (line.contains("money")) {
                money.add(amount);
            }
        }

        if (milk.size() != remainingCount) {
            return CheckResult.wrong("Your program should display the coffee machine's state " + remainingCount + " times, since the \"remaining\" action was used " + remainingCount + " times.\n" +
                    "So, there should be " + remainingCount + " lines showing the \"milk\" amount, but found " + milk.size() + " such line(s).");
        }
        if (water.size() != remainingCount) {
            return CheckResult.wrong("Your program should display the coffee machine's state " + remainingCount + " times, since the \"remaining\" action was used " + remainingCount + " times.\n" +
                    "So, there should be " + remainingCount + " lines showing the \"water\" amount, but found " + water.size() + " such line(s).");
        }
        if (coffeeBeans.size() != remainingCount) {
            return CheckResult.wrong("Your program should display the coffee machine's state " + remainingCount + " times, since the \"remaining\" action was used " + remainingCount + " times.\n" +
                    "So, there should be " + remainingCount + " lines showing the \"coffee beans\" amount, but found " + coffeeBeans.size() + " such line(s).");
        }
        if (cups.size() != remainingCount) {
            return CheckResult.wrong("Your program should display the coffee machine's state " + remainingCount + " times, since the \"remaining\" action was used " + remainingCount + " times.\n" +
                    "So, there should be " + remainingCount + " lines showing the \"cups\" amount, but found " + cups.size() + " such line(s).");
        }
        if (money.size() != remainingCount) {
            return CheckResult.wrong("Your program should display the coffee machine's state " + remainingCount + " times, since the \"remaining\" action was used " + remainingCount + " times.\n" +
                    "So, there should be " + remainingCount + " lines showing the \"money\" amount, but found " + money.size() + " such line(s).");
        }

        // Check that initial state values are correct
        if (water.get(0) != 400 || milk.get(0) != 540 || coffeeBeans.get(0) != 120
                || cups.get(0) != 9 || money.get(0) != 550) {
            String expectedState = "The coffee machine has:\n" +
                    "400 ml of water\n" +
                    "540 ml of milk\n" +
                    "120 g of coffee beans\n" +
                    "9 disposable cups\n" +
                    "$550 of money\n";

            String actualState = "The coffee machine has:\n" +
                    water.get(0) + " ml of water\n" +
                    milk.get(0) + " ml of milk\n" +
                    coffeeBeans.get(0) + " g of coffee beans\n" +
                    cups.get(0) + " disposable cups\n" +
                    "$" + money.get(0) + " of money\n";

            return CheckResult.wrong("Initial coffee machine state is incorrect!\n\n"
                    + "Expected state:\n" + expectedState + "\n"
                    + "Actual state:\n" + actualState + "\n"
            );
        }

        // Variables for difference calculations
        int milk0 = milk.get(0);
        int milk1 = milk.get(milk.size() - 1);

        int water0 = water.get(0);
        int water1 = water.get(water.size() - 1);

        int coffeeBeans0 = coffeeBeans.get(0);
        int coffeeBeans1 = coffeeBeans.get(coffeeBeans.size() - 1);

        int cups0 = cups.get(0);
        int cups1 = cups.get(cups.size() - 1);

        int money0 = money.get(0);
        int money1 = money.get(money.size() - 1);

        int actualWaterDiff = water1 - water0;
        int actualMilkDiff = milk1 - milk0;
        int actualCoffeeBeansDiff = coffeeBeans1 - coffeeBeans0;
        int actualCupsDiff = cups1 - cups0;
        int actualMoneyDiff = money1 - money0;

        boolean isCorrect =
            actualWaterDiff == expectedWaterDiff &&
            actualMilkDiff == expectedMilkDiff &&
            actualCoffeeBeansDiff == expectedCoffeeBeansDiff &&
            actualCupsDiff == expectedCupsDiff &&
            actualMoneyDiff == expectedMoneyDiff;

        return new CheckResult(isCorrect, feedback);
    }

    @DynamicTest(order = 100)
    CheckResult checkCoffeeMachineActions() {
        TestedProgram main = new TestedProgram();

        // Coffee Machine actions check
        String actualActionsPrompt = main.start();
        String actualActionsPromptLowerCase = actualActionsPrompt.trim().toLowerCase();
        String expectedActionsPrompt = "Write action (buy, fill, take, clean, remaining, exit):";

        if (!actualActionsPromptLowerCase.contains("buy") || !actualActionsPromptLowerCase.contains("fill") || !actualActionsPromptLowerCase.contains("take") || !actualActionsPromptLowerCase.contains("clean") || !actualActionsPromptLowerCase.contains("remaining") || !actualActionsPromptLowerCase.contains("exit")) {
            return CheckResult.wrong("Coffee Machine should support six actions: \"buy\", \"fill\", \"take\", \"clean\", \"remaining\", and \"exit\"\n\n"
                    + "Expected actions prompt:\n" + expectedActionsPrompt + "\n\n"
                    + "Actual actions prompt:\n" + actualActionsPrompt + "\n");
        }

        return CheckResult.correct();
    }

    @DynamicTest(order = 101)
    CheckResult checkCoffeeMachineBuyingAction() {
        TestedProgram main = new TestedProgram();

        main.start();

        // Buying Check
        String actualBuyPrompt1 = main.execute("buy");
        String actualBuyPrompt1LowerCase = actualBuyPrompt1.trim().toLowerCase();
        String expectedBuyPrompt1 = "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:";

        if (!actualBuyPrompt1LowerCase.contains("espresso") || !actualBuyPrompt1LowerCase.contains("latte") || !actualBuyPrompt1LowerCase.contains("cappuccino") || !actualBuyPrompt1LowerCase.contains("back")) {
            return CheckResult.wrong("Coffee Machine should support buying 3 types of coffees actions: \"espresso\", \"latte\", \"take\", \"cappuccino\". It should also support \"back\" action, to get to the main menu\n\n"
                    + "Expected buy prompt:\n" + expectedBuyPrompt1 + "\n\n"
                    + "Actual buy prompt:\n" + actualBuyPrompt1 + "\n");
        }

        String actualBuyOutput1 = main.execute("2");
        String actualBuyOutput1LowerCase = actualBuyOutput1.trim().toLowerCase();
        String expectedBuyOutput1 = "I have enough resources, making you a coffee!";

        if (!actualBuyOutput1LowerCase.contains(expectedBuyOutput1.toLowerCase())) {
            return CheckResult.wrong("Coffee Machine should make us coffee, since it has sufficient ingredients (and doesn't required cleaning yet).\n\n"
                    + "Expected buy output:\n" + expectedBuyOutput1 + "\n\n"
                    + "Actual buy output:\n" + actualBuyOutput1 + "\n");
        }

        String actualBuyOutput2 = main.execute("buy\n2");
        String actualBuyOutput2LowerCase = actualBuyOutput2.trim().toLowerCase();
        String expectedBuyOutput2 = "Sorry, not enough water!";

        if (!actualBuyOutput2LowerCase.contains(expectedBuyOutput2.toLowerCase())) {
            return CheckResult.wrong("Coffee Machine shouldn't be able to make us coffee, since it doesn't have sufficient ingredients.\n\n"
                    + "Expected buy output:\n" + expectedBuyOutput2 + "\n\n"
                    + "Actual buy output:\n" + actualBuyOutput2 + "\n");
        }

        return CheckResult.correct();
    }

    @DynamicTest(order = 102)
    CheckResult checkCoffeeMachineFillingAction() {
        TestedProgram main = new TestedProgram();

        main.start();

        // Filling Check (using "remaining" action, after "fill" action)
        main.execute("fill\n2750\n260\n68\n3");
        String actualRemainingOutputAfterFilling = main.execute("remaining").trim();
        String expectedRemainingOutputAfterFilling = "The coffee machine has:\n" + "3150 ml of water\n" + "800 ml of milk\n" + "188 g of coffee beans\n" + "12 disposable cups\n" + "$550 of money";

        if (!actualRemainingOutputAfterFilling.contains(Integer.toString(3150)) || !actualRemainingOutputAfterFilling.contains(Integer.toString(800)) || !actualRemainingOutputAfterFilling.contains(Integer.toString(188)) || !actualRemainingOutputAfterFilling.contains(Integer.toString(12)) || !actualRemainingOutputAfterFilling.contains(Integer.toString(550))) {
            return CheckResult.wrong("Incorrect Coffee Machine state after filling\n\n"
                    + "Expected state:\n" + expectedRemainingOutputAfterFilling + "\n\n"
                    + "Actual state:\n" + actualRemainingOutputAfterFilling + "\n");
        }

        return CheckResult.correct();
    }

    @DynamicTest(order = 103)
    CheckResult checkCoffeeMachineCleaningAction() {
        TestedProgram main = new TestedProgram();

        main.start();

        main.execute("fill\n2750\n260\n68\n3");

        main.execute("buy\n1");
        main.execute("buy\n1");
        main.execute("buy\n1");
        main.execute("buy\n2");
        main.execute("buy\n2");
        main.execute("buy\n2");
        main.execute("buy\n2");
        main.execute("buy\n3");
        main.execute("buy\n3");
        main.execute("buy\n3");

        // "buy" check, after before "clean"
        String actualBuyOutputBeforeCleaning = main.execute("buy");
        String actualBuyOutputBeforeCleaningLowerCase = actualBuyOutputBeforeCleaning.trim().toLowerCase();
        String expectedBuyOutputBeforeCleaning = "I need cleaning!";

        if (!actualBuyOutputBeforeCleaningLowerCase.contains(expectedBuyOutputBeforeCleaning.toLowerCase())) {
            return CheckResult.wrong("Coffee Machine requires cleaning after producing 10 cups of coffee\n\n"
                    + "Expected output:\n" + expectedBuyOutputBeforeCleaning + "\n\n"
                    + "Actual buy output:\n" + actualBuyOutputBeforeCleaning + "\n");
        }

        String actualBuyOutputAfterCleaning = main.execute("clean");
        String actualBuyOutputAfterCleaningLowerCase = actualBuyOutputAfterCleaning.trim().toLowerCase();
        String expectedBuyOutputAfterCleaning = "I have been cleaned!";

        if (!actualBuyOutputAfterCleaningLowerCase.contains(expectedBuyOutputAfterCleaning.toLowerCase())) {
            return CheckResult.wrong("After cleaning, the Coffee Machine should output that it has been cleaned. \n\n"
                    + "Expected output:\n" + expectedBuyOutputAfterCleaning + "\n\n"
                    + "Actual output:\n" + actualBuyOutputAfterCleaning + "\n");
        }


        main.execute("buy");
        String actualBuyOutput1 = main.execute("3");
        String actualBuyOutput1LowerCase = actualBuyOutput1.trim().toLowerCase();
        String expectedBuyOutput1 = "I have enough resources, making you a coffee!";

        if (!actualBuyOutput1LowerCase.contains(expectedBuyOutput1.toLowerCase())) {
            return CheckResult.wrong("Coffee Machine should make us coffee, since it has sufficient ingredients (and doesn't required cleaning yet).\n\n"
                    + "Expected buy output:\n" + expectedBuyOutput1 + "\n\n"
                    + "Actual buy output:\n" + actualBuyOutput1 + "\n");
        }

        main.execute("buy\n3");

        main.execute("fill\n1234\n522\n89\n7");

        String actualRemainingOutputAfterFilling = main.execute("remaining").trim();
        String expectedRemainingOutputAfterFilling = "The coffee machine has:\n" + "1234 ml of water\n" + "522 ml of milk\n" + "89 g of coffee beans\n" + "7 disposable cups\n" + "$620 of money";

        if (!actualRemainingOutputAfterFilling.contains(Integer.toString(1234)) || !actualRemainingOutputAfterFilling.contains(Integer.toString(522)) || !actualRemainingOutputAfterFilling.contains(Integer.toString(89)) || !actualRemainingOutputAfterFilling.contains(Integer.toString(7)) || !actualRemainingOutputAfterFilling.contains(Integer.toString(620))) {
            return CheckResult.wrong("Incorrect Coffee Machine state after filling\n\n"
                    + "Expected state:\n" + expectedRemainingOutputAfterFilling + "\n\n"
                    + "Actual state:\n" + actualRemainingOutputAfterFilling + "\n");
        }

        return CheckResult.correct();
    }

    @DynamicTest(order = 104)
    CheckResult checkCoffeeMachineExitAction() {
        TestedProgram main = new TestedProgram();

        main.start();

        // Exit Check
        main.execute("exit");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!main.isFinished()) {
            return CheckResult.wrong("Your program should terminate after receiving the \"exit\" command.");
        }

        return CheckResult.correct();
    }
}
