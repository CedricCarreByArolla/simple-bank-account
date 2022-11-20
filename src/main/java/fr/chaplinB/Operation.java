package fr.chaplinB;

import java.time.LocalDate;

public record Operation(OperationType operationType, LocalDate localDate, Amount amount) {
}
