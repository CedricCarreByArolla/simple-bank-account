package fr.chaplinB;

public class BankAccount {

    private Amount balance;

    public BankAccount(Amount initialAmountToDeposit) {

        balance = initialAmountToDeposit;
    }

    public void deposit(Amount amountToDeposit) {
        balance = balance.plus(amountToDeposit);
    }

    public void withdraw(Amount amountToWithdraw) {
        throw new UnsupportedOperationException();
    }
    public Amount getBalance() {
        return balance;
    }

}
