package com.opera.util;

import com.opera.annotations.EmailConstraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements
        ConstraintValidator<EmailConstraint, String> {
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Override
    public boolean isValid(String emailField,
                           ConstraintValidatorContext cxt) {
        return emailField != null && emailField.matches(EMAIL_REGEX)
                && (emailField.length() > 3) && (emailField.length() < 254);
    }
}
