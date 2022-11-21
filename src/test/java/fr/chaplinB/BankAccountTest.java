package fr.chaplinB;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class BankAccountTest {

    private BigDecimal initialAmountAsBigDecimal;
    private Amount initialAmountToDeposit;
    private AccountStatement accountStatement;
    private BankAccount bankAccount;

    @BeforeEach
    void initialize() {
        initialAmountAsBigDecimal = new BigDecimal("1500.00");
        initialAmountToDeposit = Amount.valueOf(initialAmountAsBigDecimal);
        accountStatement = new AccountStatement();
        ClockTicker clockTicker = new ClockTicker();
        bankAccount = new BankAccount(initialAmountToDeposit, accountStatement, clockTicker);
    }

    @Test
    void
    given_an_new_account_with_an_initial_deposit_of_an_amount_of_1500_when_we_deposit_500_should_return_a_balance_of_2000() {
        //Arrange
        BigDecimal amountToDepositAsBigDecimal = new BigDecimal("500.00");
        Amount amountToDeposit = Amount.valueOf(amountToDepositAsBigDecimal);
        //Act
        bankAccount.deposit(amountToDeposit);
        //Assert
        BigDecimal expectedAsBigDecimal = new BigDecimal("2000.00");
        Amount expectedBalance = Amount.valueOf(expectedAsBigDecimal);

        assertThat(bankAccount.getBalance()).isEqualTo(expectedBalance);
    }

    @Test
    void given_an_new_account_with_an_initial_deposit_of_an_amount_of_1500_when_I_withdraw_1500_should_update_balance_to_zero() {
        //Arrange
        BigDecimal amountToWithdrawAsBigDecimal = new BigDecimal("1500.00");
        Amount amountToWithdraw = Amount.valueOf(amountToWithdrawAsBigDecimal);
        //Act
        bankAccount.withdraw(amountToWithdraw);
        //Assert
        BigDecimal expectedResultAsBigDecimal = new BigDecimal("0.00");
        Amount expectedBalance = Amount.valueOf(expectedResultAsBigDecimal);

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
    void should_record_an_operation_in_operations_when_I_open_an_account_with_the_amount_of_my_initial_deposit() {
        //Arrange
        Operation operation = new Operation(OperationType.DEPOSIT, LocalDate.of(2022, 12, 10), initialAmountToDeposit);
        //Act

        //Assert
        List<Operation> expectedResult = new ArrayList<>() {{
            add(operation);
        }};

        assertThat(bankAccount.getAccountStatement()).isEqualTo(expectedResult);
    }

    @Test
    void should_record_2_operations_in_operations_when_I_withdraw_500_the_day_after() {
        //Arrange
        BigDecimal amountToDepositAsBigDecimal = new BigDecimal("500.00");
        Amount amountToDeposit = Amount.valueOf(amountToDepositAsBigDecimal);

        Operation depositWhenCreated = new Operation(OperationType.DEPOSIT, LocalDate.of(2022, 12, 10), initialAmountToDeposit);
        Operation withdrawal = new Operation(OperationType.WITHDRAWAL, LocalDate.of(2022, 12, 11), amountToDeposit);

        //Act
        bankAccount.withdraw(amountToDeposit);
        //Assert
        List<Operation> expectedResult = new ArrayList<>() {{
            add(depositWhenCreated);
            add(withdrawal);
        }};

        assertThat(bankAccount.getAccountStatement()).isEqualTo(expectedResult);
    }
}
