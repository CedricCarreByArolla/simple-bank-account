package fr.chaplinB;

import java.util.List;

public class StatementPrinter {
    public void print(List<Operation> allOperations) {
        System.out.println("Operation | Date | Amount");
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
}
