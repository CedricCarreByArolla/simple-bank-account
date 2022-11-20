package fr.chaplinB;

import java.math.BigDecimal;
import java.util.Objects;

public final class Amount {
    private final BigDecimal amount;

    private Amount(BigDecimal amountAsBigDecimal) {
        amount = amountAsBigDecimal;
    }

    public static Amount valueOf(BigDecimal amountAsBigDecimal) {
        if(isNegativeNumber(amountAsBigDecimal))
            throw new IllegalAmountException("Amount must be a positive number");
        return new Amount(amountAsBigDecimal);
    }

    private static boolean isNegativeNumber(BigDecimal amountAsBigDecimal) {
        return BigDecimal.ZERO.compareTo(amountAsBigDecimal) > 0;
    }

    public Amount plus(Amount anotherAmount) {
        return new Amount(amount.add(anotherAmount.amount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount that = (Amount) o;
        return Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Amount{" +
                "amount=" + amount +
                '}';
    }
}
