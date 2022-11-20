package fr.chaplinB;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

public class BankAccount {

    private Amount balance = Amount.valueOf(new BigDecimal("0.00"));
    private AccountStatement accountStatement;
    private Clock clock;

    public BankAccount(Amount initialAmountToDeposit, AccountStatement accountStatement, Clock clock) {
        this.accountStatement = accountStatement;
        this.clock = clock;
        deposit(initialAmountToDeposit);
    }

    public void deposit(Amount amountToDeposit) {
        balance = balance.plus(amountToDeposit);
        accountStatement.add(new Operation(OperationType.DEPOSIT, LocalDate.now(clock), amountToDeposit));
    }

    public void withdraw(Amount amountToWithdraw) {
        if(this.balance.compareTo(amountToWithdraw) < 0)
            throw new NotEnoughMoneyException("Vous n'avez pas assez d'argent pour effectuer cette opération !");
        balance = balance.minus(amountToWithdraw);
        accountStatement.add(new Operation(OperationType.WITHDRAWAL, LocalDate.now(clock), amountToWithdraw));
    }
    public Amount getBalance() {

        Amount deposit = getAccountStatement()
                .stream()
                .filter(operation -> operation.operationType().equals(OperationType.DEPOSIT))
                .parallel()
                .reduce(Amount.valueOf(new BigDecimal("0.00")), (operation1, operation2) -> operation1.plus(operation2.amount()), Amount::plus);


        Amount withdrawal = getAccountStatement()
                .stream()
                .filter(operation -> operation.operationType().equals(OperationType.WITHDRAWAL))
                .parallel()
                .reduce(Amount.valueOf(new BigDecimal("0.00")), (operation1, operation2) -> operation1.plus(operation2.amount()), Amount::plus);

        return deposit.minus(withdrawal);
    }

    public List<Operation> getAccountStatement() {
        return this.accountStatement.getAll();
    }
}
