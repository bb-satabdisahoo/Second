package com.example.unittesting;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserNameValidatorTest {

    @Test
    public void userNameValidator_Capital_ReturnsTrue(){
        assertTrue(UserNameValidator.isValidName("Satabdi"));
    }

    @Test
    public void userNameValidator_Small_ReturnsTrue(){
        assertTrue(UserNameValidator.isValidName("satabdi"));
    }

    @Test
    public void userNameValidator_SpecialCharacter_Capital_ReturnsFalse() {
        assertFalse(UserNameValidator.isValidName("Satabdi@"));
    }
    @Test
    public void userNameValidator_SpecialCharacter_Small_ReturnsFalse() {
        assertFalse(UserNameValidator.isValidName("satabdi@"));
    }

    @Test
    public void userNameValidator_Number_ReturnsFalse() {
        assertFalse(UserNameValidator.isValidName("Satabdi@"));
    }

    @Test
    public void userNameValidator_EmptyString_ReturnsFalse() {
        assertFalse(UserNameValidator.isValidName(""));
    }

    @Test
    public void userNameValidator_NullName_ReturnsFalse() {
        assertFalse(UserNameValidator.isValidName(null));
    }
}
