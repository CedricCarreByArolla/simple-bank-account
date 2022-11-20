package fr.chaplinB;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class BankAccountTest {

    BigDecimal initialAmountAsBigDecimal;
    Amount initialAmountToDeposit;
    BankAccount bankAccount;

    @BeforeEach
    void initialize() {
        initialAmountAsBigDecimal = new BigDecimal("1500.00");
        initialAmountToDeposit = Amount.valueOf(initialAmountAsBigDecimal);
        bankAccount = new BankAccount(initialAmountToDeposit);
    }

    @Test
    void should_be_always_true() {
        assertThat(true).isTrue();
    }

    @Test
    void
    given_an_new_account_with_an_initial_deposit_of_an_amount_of_1500_when_we_deposit_500_should_return_a_balance_of_2000() {
        //Arrange
        BigDecimal amountToDepositAsBigDecimal = new BigDecimal("500.00");
        Amount amountToDeposit = Amount.valueOf(amountToDepositAsBigDecimal);

        BigDecimal expectedAsBigDecimal = new BigDecimal("2000.00");
        Amount expectedBalance = Amount.valueOf(expectedAsBigDecimal);
        //Act
        bankAccount.deposit(amountToDeposit);
        //Assert
        assertThat(bankAccount.getBalance()).isEqualTo(expectedBalance);
    }

    @Test
    void given_an_new_account_with_an_initial_deposit_of_an_amount_of_1500_when_I_withdraw_1500_should_update_balance_to_zero() {
        //Arrange
        BigDecimal amountToWithdrawAsBigDecimal = new BigDecimal("1500.00");
        Amount amountToWithdraw = Amount.valueOf(amountToWithdrawAsBigDecimal);

        BigDecimal expectedResultAsBigDecimal = new BigDecimal("0.00");

        Amount expectedBalance = Amount.valueOf(expectedResultAsBigDecimal);
        //Act
        bankAccount.withdraw(amountToWithdraw);
        //Assert
        assertThat(bankAccount.getBalance()).isEqualTo(expectedBalance);
    }

    @Test
    void should_raise_NotEnoughMoneyException_when_I_try_to_withdraw_an_amount_of_1501_when_the_balance_is_1500() {
        //Arrange
        BigDecimal amountAsBigDecimal = new BigDecimal("1501.00");
        Amount amountToWithdraw = Amount.valueOf(amountAsBigDecimal);
        //Act
        Throwable thrown = catchThrowable(() -> bankAccount.withdraw(amountToWithdraw));
        //Assert
        assertThat(thrown).isInstanceOf(NotEnoughMoneyException.class);
    }

    @Test
    void should_record_an_operation_in_operations_when_I_open_an_account_whenever_the_amount_of_my_initial_deposit() {
        //Arrange
        Operation operation = new Operation(OperationType.DEPOSIT, LocalDate.of(2022, 12, 10), initialAmountToDeposit);
        List<Operation> expectedResult = new ArrayList<Operation>(){{
            add(operation);
        }};
        //Act

        //Assert
        assertThat(bankAccount.getAccountStatement().getall()).isEqualTo(expectedResult);
    }
}
