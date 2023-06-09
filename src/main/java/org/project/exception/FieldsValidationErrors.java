package org.project.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class FieldsValidationErrors {

    public static void returnErrorsToClient(BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorMsg.append(error.getField())
                    .append(" - ").append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                    .append(";\n");
        }
        throw new EntitiyNotCreatedException(errorMsg.toString());
    }
}
