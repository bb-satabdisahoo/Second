package com.example.unittesting;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PasswordValidatorTest {

    @Test
    public void passwordValidator_ReturnsTrue(){
        assertTrue(PasswordValidator.isValidPassword("Sata@1"));
    }

    @Test
    public void passwordValidator_Small_ReturnsFalse() {
        assertFalse(PasswordValidator.isValidPassword("sata@1"));
    }

    @Test
    public void passwordValidator_NoSpecial_ReturnsFalse() {
        assertFalse(PasswordValidator.isValidPassword("Sata1"));
    }

    @Test
    public void passwordValidator_NoNumber_ReturnsFalse() {
        assertFalse(PasswordValidator.isValidPassword("Sata@"));
    }

    @Test
    public void passwordValidator_EmptyString_ReturnsFalse() {
        assertFalse(PasswordValidator.isValidPassword(""));
    }

    @Test
    public void passwordValidator_Null_ReturnsFalse() {
        assertFalse(PasswordValidator.isValidPassword(null));
    }
}
