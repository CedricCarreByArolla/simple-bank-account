package fr.chaplinB;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountTest {

    @Test
    void should_be_always_true() {
        assertThat(true).isTrue();
    }

    @Test
    void
    given_an_new_account_with_an_initial_deposit_of_an_amount_of_1500_when_we_deposit_500_should_return_a_balance_of_2000() {
        //Arrange
        BigDecimal initialAmountAsBigDecimal = new BigDecimal("1500.00");
        Amount initialAmountToDeposit = new Amount(initialAmountAsBigDecimal);

        BankAccount bankAccount = new BankAccount(initialAmountToDeposit);

        BigDecimal amountToDepositAsBigDecimal = new BigDecimal("500.00");
        Amount amountToDeposit = new Amount(amountToDepositAsBigDecimal);

        BigDecimal expectedAsBigDecimal = new BigDecimal("2000.00");
        Amount expectedBalance = new Amount(expectedAsBigDecimal);
        //Act
        bankAccount.deposit(amountToDeposit);
        //Assert
        assertThat(bankAccount.getBalance()).isEqualTo(expectedBalance);
    }
}
