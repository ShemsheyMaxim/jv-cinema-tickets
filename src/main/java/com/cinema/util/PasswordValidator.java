package com.cinema.util;

import com.cinema.annotations.PasswordConstraint;
import com.cinema.model.dto.UserRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordValidator implements
        ConstraintValidator<PasswordConstraint, UserRequestDto> {
    public static final String PASSWORD_REGEX
            = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\\\S+$).{6,128}$";
    private String field;
    private String fieldMatch;

    @Override
    public void initialize(PasswordConstraint password) {
        this.field = password.field();
        this.fieldMatch = password.fieldMatch();
    }

    @Override
    public boolean isValid(UserRequestDto user,
                           ConstraintValidatorContext context) {
        if (user.getPassword() != null && user.getPassword().matches(PASSWORD_REGEX)
                && (user.getPassword().length() > 6) && (user.getPassword().length() < 128)) {
            Object fieldValue = new BeanWrapperImpl(user)
                    .getPropertyValue(field);
            Object fieldMatchValue = new BeanWrapperImpl(user)
                    .getPropertyValue(fieldMatch);
            return fieldValue.equals(fieldMatchValue);
        }
        return false;
    }
}
