package com.nissum.test.userservice.util.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ValidationHandler Class
 *
 * @author <a href="mailto:sagitario.wilbert@gmail.com">Wilbert Marcia</a>
 * @version 1.0
 * @since 02-22-2023
 */
@Component
public class ValidationHandler {

    @Value("${validation.expression.email}")
    private String validationExpresionEmail;

    @Value("${validation.expression.password}")
    private String validationExpresioPassword;

    /**
     * Validate Format Email RFC 5322
     *
     * @param email
     * @return boolean
     */
    public boolean validateFormatEmail(String email) {
        Pattern pattern = Pattern.compile(validationExpresionEmail);

        Matcher mather = pattern.matcher(email);

        return mather.find();
    }

    /**
     * Validate Format Password
     * 4 to 8 character password requiring numbers and both lowercase and uppercase letters
     *
     * @param password
     * @return boolean
     */
    public boolean validateFormatPassword(String password) {
        Pattern pattern = Pattern.compile(validationExpresioPassword);

        Matcher mather = pattern.matcher(password);

        return mather.find();
    }

}
