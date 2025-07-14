import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Splitwise {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Double> expenses = new HashMap<>();

        System.out.print("Enter the number of friends: ");
        int numFriends = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numFriends; i++) {
            System.out.print("Enter the name of friend " + (i + 1) + ": ");
            String friendName = scanner.nextLine();
            System.out.print("Enter the amount spent by " + friendName + ": ");
            double amountSpent = scanner.nextDouble();
            scanner.nextLine();
            expenses.put(friendName, amountSpent);
        }

        System.out.println("Expense details:");
        for (Map.Entry<String, Double> entry : expenses.entrySet()) {
            System.out.println(entry.getKey() + " spent $" + entry.getValue());
        }

        double totalExpense = expenses.values().stream().mapToDouble(Double::doubleValue).sum();
        double averageExpense = totalExpense / numFriends;

        System.out.println("Total expense: $" + totalExpense);
        System.out.println("Average expense per person: $" + averageExpense);

        System.out.println("Individual balances:");
        for (Map.Entry<String, Double> entry : expenses.entrySet()) {
            double balance = entry.getValue() - averageExpense;
            System.out.println(entry.getKey() + " owes $" + balance);
        }
    }
}
