package fr.chaplinB;

import java.math.BigDecimal;

public final class Amount {
    private final BigDecimal amount;

    public Amount(BigDecimal amountAsBigDecimal) {
        amount = amountAsBigDecimal;
    }

    public Amount plus(Amount anotherAmount) {
        return new Amount(amount.add(anotherAmount.amount));
    }
}
