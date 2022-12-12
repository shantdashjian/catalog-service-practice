package com.polarbookshop.catalogservice.domain;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookValidationTests {

    static Validator validator;

    @BeforeAll
    static void setUp() {
        var factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        Book book =
                new Book("1234567891", "CN", "Vitale", 19.99);
        var violations = validator.validate(book);
        assertThat(violations).hasSize(0);
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        Book book =
                new Book("123456789", "CN", "Vitale", 19.99);
        var violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Book ISBN format must be valid.");
    }
}
