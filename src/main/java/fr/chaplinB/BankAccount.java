package fr.chaplinB;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

public class BankAccount {
    public static final Amount AMOUNT_ZERO = Amount.valueOf(new BigDecimal("0.00"));
    private final AccountStatement accountStatement;

    private StatementPrinter statementPrinter = new StatementPrinter();
    private final Clock clock;

    public BankAccount(Amount initialAmountToDeposit, AccountStatement accountStatement, Clock clock) {
        this.accountStatement = accountStatement;
        this.clock = clock;
        deposit(initialAmountToDeposit);
    }

    public void deposit(Amount amountToDeposit) {
        accountStatement.add(new Operation(OperationType.DEPOSIT, LocalDate.now(clock), amountToDeposit));
    }

    public void withdraw(Amount amountToWithdraw) {
        if (isNotAuthorizedWithdrawal(amountToWithdraw)) {
            throw new NotEnoughMoneyException("Vous n'avez pas assez d'argent pour effectuer cette opération !");
        }
        accountStatement.add(new Operation(OperationType.WITHDRAWAL, LocalDate.now(clock), amountToWithdraw));
    }

    private boolean isNotAuthorizedWithdrawal(Amount amountToWithdraw) {
        return getBalance().compareTo(amountToWithdraw) < 0;
    }

    public Amount getBalance() {

        Amount totalOfDeposits = getAccountStatement()
                .stream()
                .filter(operation -> operation.operationType().equals(OperationType.DEPOSIT))
                .parallel()
                .reduce(AMOUNT_ZERO, (operation, nextOperation) -> operation.plus(nextOperation.amount()), Amount::plus);


        Amount totalOfWithdrawal = getAccountStatement()
                .stream()
                .filter(operation -> operation.operationType().equals(OperationType.WITHDRAWAL))
                .parallel()
                .reduce(AMOUNT_ZERO, (operation, nextOperation) -> operation.plus(nextOperation.amount()), Amount::plus);

        return totalOfDeposits.minus(totalOfWithdrawal);
    }

    public List<Operation> getAccountStatement() {
        return this.accountStatement.getAll();
    }

    public void printStatement() {
        statementPrinter.print(getAccountStatement(), getBalance());
    }
}
