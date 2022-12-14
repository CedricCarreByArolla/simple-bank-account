package fr.chaplinB;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class AmountTest {
    @Test
    void two_instance_of_Amount_should_be_equals_if_their_attributes_value_are_equals() {
        //Arrange
        BigDecimal amountAsBig = new BigDecimal("100.01");
        //Act
        Amount amount = Amount.valueOf(amountAsBig);
        Amount anotherAmount = Amount.valueOf(amountAsBig);
        //Assert
        assertThat(amount).isEqualTo(anotherAmount);
    }

    @Test
    void should_always_be_positive_number() {
        //Arrange
        BigDecimal negativeNumber = new BigDecimal("-100.00");
        //Act
        Throwable thrown = catchThrowable(() -> Amount.valueOf(negativeNumber));
        //Assert
        assertThat(thrown).isInstanceOf(IllegalAmountException.class);
    }

    @Test
    void should_thrown_an_IllegalOperation_when_Amount_minus_bigger_Amount() {
        //Arrange
        BigDecimal firstBig = new BigDecimal("100.00");
        BigDecimal secondBig = new BigDecimal("200.00");

        Amount amount = Amount.valueOf(firstBig);
        Amount biggerAmount = Amount.valueOf(secondBig);
        //Act
        Throwable thrown = catchThrowable(() -> amount.minus(biggerAmount));
        //Assert
        assertThat(thrown).isInstanceOf(IllegalAmountException.class);
    }
}