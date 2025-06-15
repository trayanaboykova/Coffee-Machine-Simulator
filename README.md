# ☕ Coffee Machine Simulator

This console-based Coffee Machine Simulator is a hands-on Java project designed to replicate the functionality of a real-world coffee machine. Built as part of my Java learning journey, it reinforces object-oriented programming concepts while providing a fun and interactive experience.

## 🚀 Project Overview
The simulator progresses through multiple stages, gradually adding features and complexity:

- Ingredient Calculator – Computes needed water, milk, and coffee beans per cup.
- Resource Check – Determines whether the machine can make the requested number of cups.
- Basic Commands – Allows users to buy coffee, refill resources, and take cash from the machine.
- Looping Menu – Adds an interactive main loop for repeated use until exit.
- Object-Oriented Refactor – Code is modularized into classes for better structure and scalability.
- Cleaning Feature – After every 10 cups, the machine must be cleaned before continuing.

## 🎯 What I Learned
- ☕ OOP Fundamentals: Applied encapsulation and class responsibilities using `Coffee`, `CoffeeMachineCore`, and `CoffeeMachine` classes.
- 🔄 Control Flow: Used loops and `switch` statements to manage user interaction.
- 📥 Scanner Input Handling: Collected and processed dynamic user input through a structured menu.
- 🧮 State Management: Tracked water, milk, beans, cups, money, and cleaning needs.
- ⚙️ Functional Decomposition: Broke down tasks into clean, reusable methods across multiple classes.

## 🔧 Features
- ✅ Buy Coffee: Choose between espresso, latte, or cappuccino; calculates ingredients and updates stock.
- ✅ Refill Machine: Add water, milk, beans, and cups as needed.
- ✅ Take Money: Withdraw accumulated cash from sales.
- ✅ View Remaining Resources: Show current machine state at any time.
- ✅ Cleaning System: After 10 coffees, the machine will stop until cleaned.
- ✅ Menu-Driven Interface: Easy-to-use CLI with six available commands: `buy`, `fill`, `take`, `clean`, `remaining`, `exit`.

## 📂 Project Structure

`CoffeeMachine.java` – Main entry point. Handles the CLI loop and user actions.

`CoffeeMachineCore.java` – Encapsulates the machine’s internal logic and state.

`Coffee.java` – A simple class representing the ingredients and cost of a coffee type.

## 🛠️ Technologies I Used
[![Java](https://skillicons.dev/icons?i=java&theme=light)](https://www.java.com/)  
[![IntelliJ IDEA](https://skillicons.dev/icons?i=idea&theme=light)](https://www.jetbrains.com/idea/)

🤔 How to Run
1. **Clone the repository**:  
   ```bash
   https://github.com/trayanaboykova/Coffee-Machine-Simulator.git
2. Open the project in your favorite Java IDE (e.g., IntelliJ IDEA).
3. Compile and run the `Main.java` file.
4. Follow on-screen menu to show seats, buy tickets, view stats, or exit.

## 📈 Learning Outcomes
By completing this project, I:

Practiced OOP principles like encapsulation, separation of concerns, and modular design.

Gained experience structuring CLI-based applications with multiple state transitions.

Improved my ability to write maintainable and scalable Java code.

## 🌟 Acknowledgments
Thanks to JetBrains Academy for the project template and the Java community for continuous inspiration and best practices!

