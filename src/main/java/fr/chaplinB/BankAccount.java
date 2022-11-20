package fr.chaplinB;

public class BankAccount {

    private Amount balance;

    public BankAccount(Amount initialAmountToDeposit) {

        balance = initialAmountToDeposit;
    }

    public void deposit(Amount amountToDeposit) {
        throw new UnsupportedOperationException();
    }

    public Amount getBalance() {
        return balance;
    }
}
