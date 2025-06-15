package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachineCore machine = new CoffeeMachineCore();

        while (true) {
            System.out.println("\nWrite action (buy, fill, take, clean, remaining, exit):");
            String action = scanner.next();

            switch (action) {
                case "buy" -> machine.buy(scanner);
                case "fill" -> machine.fill(scanner);
                case "take" -> machine.take();
                case "clean" -> machine.clean();
                case "remaining" -> machine.printState();
                case "exit" -> {
                    return;
                }
                default -> System.out.println("Unknown action.");
            }
        }
    }
}

class Coffee {
    int water;
    int milk;
    int beans;
    int cost;

    Coffee(int water, int milk, int beans, int cost) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cost = cost;
    }
}

class CoffeeMachineCore {
    private int water = 400;
    private int milk = 540;
    private int beans = 120;
    private int cups = 9;
    private int money = 550;
    private int cupsMade = 0;
    private boolean needsCleaning = false;

    void buy(Scanner scanner) {
        if (needsCleaning) {
            System.out.println("I need cleaning!");
            return;
        }

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String choice = scanner.next();

        Coffee coffee = switch (choice) {
            case "1" -> new Coffee(250, 0, 16, 4);
            case "2" -> new Coffee(350, 75, 20, 7);
            case "3" -> new Coffee(200, 100, 12, 6);
            case "back" -> null;
            default -> {
                System.out.println("Invalid selection.");
                yield null;
            }
        };

        if (coffee == null) return;

        if (water < coffee.water) {
            System.out.println("Sorry, not enough water!");
        } else if (milk < coffee.milk) {
            System.out.println("Sorry, not enough milk!");
        } else if (beans < coffee.beans) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            water -= coffee.water;
            milk -= coffee.milk;
            beans -= coffee.beans;
            cups--;
            money += coffee.cost;
            cupsMade++;
            if (cupsMade >= 10) {
                needsCleaning = true;
            }
        }
    }

    void fill(Scanner scanner) {
        System.out.println("Write how many ml of water you want to add:");
        water += scanner.nextInt();

        System.out.println("Write how many ml of milk you want to add:");
        milk += scanner.nextInt();

        System.out.println("Write how many grams of coffee beans you want to add:");
        beans += scanner.nextInt();

        System.out.println("Write how many disposable cups you want to add:");
        cups += scanner.nextInt();
    }

    void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    void clean() {
        cupsMade = 0;
        needsCleaning = false;
        System.out.println("I have been cleaned!");
    }

    void printState() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(beans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + money + " of money");
    }
}
