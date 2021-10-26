package com.example.unittesting;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.regex.Pattern;

public class UserNameValidator implements TextWatcher {

    public static final Pattern NAME_PATTERN = Pattern.compile("[a-z A-Z]+");

    private boolean mIsValid = false;
    public boolean isValid() {
        return mIsValid;
    }

    public static boolean isValidName(CharSequence name) {
        return name != null && NAME_PATTERN.matcher(name).matches() && name.length()<15;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        mIsValid = isValidName(editable);
    }
}
