package fr.chaplinB;

import java.util.List;

public class BankAccount {

    private Amount balance;
    private AccountStatement accountStatement;

    public BankAccount(Amount initialAmountToDeposit) {

        balance = initialAmountToDeposit;
    }

    public void deposit(Amount amountToDeposit) {
        balance = balance.plus(amountToDeposit);
    }

    public void withdraw(Amount amountToWithdraw) {
        if(this.balance.compareTo(amountToWithdraw) < 0)
            throw new NotEnoughMoneyException("Vous n'avez pas assez d'argent pour effectuer cette opération !");
        balance = balance.minus(amountToWithdraw);
    }
    public Amount getBalance() {
        return balance;
    }

    public List<Operation> getAccountStatement() {
        return this.accountStatement.getAll();
    }
}
