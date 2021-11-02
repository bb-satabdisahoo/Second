package com.example.instrumentationtesting.validator;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.regex.Pattern;

public class UserAddressValidator implements TextWatcher {

    public static final Pattern ADDRESS_PATTERN = Pattern.compile("[a-z A-Z].*[,][a-z A-Z].*");

    private boolean mIsValid = false;
    public boolean isValid() {
        return mIsValid;
    }

    public static boolean isValidAddress(CharSequence address) {
        return address != null && ADDRESS_PATTERN.matcher(address).matches();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        mIsValid = isValidAddress(editable);
    }

}
