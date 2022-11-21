package fr.chaplinB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountStatement {
    List<Operation> operations = new ArrayList<>();

    public List<Operation> getAll() {
        return Collections.unmodifiableList(operations);
    }

    public void add(Operation operation) {
        operations.add(operation);
    }
}