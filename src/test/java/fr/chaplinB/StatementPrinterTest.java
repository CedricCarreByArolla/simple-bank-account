package fr.chaplinB;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class StatementPrinterTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private ClockTicker clockTicker;
    private Amount initialAmount;
    private AccountStatement accountStatement;
    private BankAccount bankAccount;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        clockTicker = new ClockTicker();
        initialAmount = Amount.valueOf(new BigDecimal("200.00"));
        accountStatement = new AccountStatement();
        bankAccount = new BankAccount(initialAmount, accountStatement, clockTicker);
    }

    @Test
    void should_print_account_statement_header_and_initial_operation_and_footer_as_balance() {
        //Arrange

        //Act
        bankAccount.printStatement();
        //Assert
        String expectedResult = """
                Operation | Date | Amount\r
                DEPOSIT | 2022-12-10 | 200.00\r
                Your balance : 200.00""";
        assertThat(outputStreamCaptor.toString().trim()).isEqualTo(expectedResult);
    }

    @Test
    void should_print_account_statement_consistent_with_my_operations() {
        //Arrange
        BigDecimal amountToDepositAsBigDecimal = new BigDecimal("1500.00");
        Amount amountToDeposit = Amount.valueOf(amountToDepositAsBigDecimal);
        BigDecimal amountToWithdrawAsBigDecimal = new BigDecimal("1000.00");
        Amount amountToWithdraw = Amount.valueOf(amountToWithdrawAsBigDecimal);
        //Act
        bankAccount.deposit(amountToDeposit);
        bankAccount.withdraw(amountToWithdraw);
        bankAccount.printStatement();
        //Assert
        String expectedResult = """
                Operation | Date | Amount\r
                DEPOSIT | 2022-12-10 | 200.00\r
                DEPOSIT | 2022-12-11 | 1500.00\r
                WITHDRAWAL | 2022-12-12 | 1000.00\r
                Your balance : 700.00""";
        assertThat(outputStreamCaptor.toString().trim()).isEqualTo(expectedResult);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
