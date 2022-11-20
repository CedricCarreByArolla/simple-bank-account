package fr.chaplinB;

import java.util.ArrayList;
import java.util.List;

public class AccountStatement {
    List<Operation> operations = new ArrayList<>();

    public List<Operation> getAll() {
        return operations;
    }

    public void add(Operation operation) {
        operations.add(operation);
    }
}