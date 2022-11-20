package fr.chaplinB;

import java.time.LocalDate;

public record Operation {
    public Operation(OperationType operationType, LocalDate localDate, Amount amount) {
    }
}
