package fr.chaplinB;

import java.util.List;

public class StatementPrinter {

    public static final String HEADER = "Operation | Date | Amount";

    public void print(List<Operation> allOperations, Amount balance) {
        printHeader();
        printStatementLines(allOperations);
        printBalance("Your balance : " + balance);
    }

    private void printStatementLines(List<Operation> allOperations) {
        allOperations.forEach(
                operation -> {
                    System.out.println(operation.operationType().toString() +
                            " | " +
                            operation.localDate().toString() +
                            " | " +
                            operation.amount().toString());
                }
        );
    }

    private void printHeader() {
        System.out.println(HEADER);
    }
    private void printBalance(String balance) {
        System.out.println(balance);
    }
}
