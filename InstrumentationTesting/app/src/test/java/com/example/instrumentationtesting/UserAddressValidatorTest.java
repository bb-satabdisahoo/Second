package com.example.instrumentationtesting;

import com.example.instrumentationtesting.validator.UserAddressValidator;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class UserAddressValidatorTest {
    @Test
    public void userAddressValidator_ReturnsTrue(){
        assertTrue(UserAddressValidator.isValidAddress("Satabdi,Satabdi"));
    }
    @Test
    public void userAddressValidator_Capital_ReturnsTrue(){
        assertTrue(UserAddressValidator.isValidAddress("SAT,SAT"));
    }

    @Test
    public void userAddressValidator_Small_ReturnsTrue(){
        assertTrue(UserAddressValidator.isValidAddress("satabdi,satabdi"));
    }

    @Test
    public void userAddressValidator_ReturnsFalse(){
        assertFalse(UserAddressValidator.isValidAddress("satabdi satabdi"));
    }

    @Test
    public void userAddressValidator_Small_ReturnsFalse(){
        assertFalse(UserAddressValidator.isValidAddress("satabdi"));
    }

    @Test
    public void userAddressValidator_EmptyString_ReturnsFalse() {
        assertFalse(UserAddressValidator.isValidAddress(""));
    }

    @Test
    public void userAddressValidator_NullAddress_ReturnsFalse() {
        assertFalse(UserAddressValidator.isValidAddress(null));
    }
}
