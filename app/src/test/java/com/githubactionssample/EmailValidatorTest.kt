package com.githubactionssample

import com.githubactionssample.util.EmailValidator
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


/**
 * Unit tests for the EmailValidator logic.
 */
class EmailValidatorTest {

    @Test
    fun emailValidator_isCorrectEmail() {
        assertTrue(EmailValidator.isValidEmail("name@email.com"))
    }

    @Test
    fun emailValidator_isCorrectEmailSubDomain() {
        assertTrue(EmailValidator.isValidEmail("name@email.co.uk"))
    }

    @Test
    fun emailValidator_invalidEmailNoTld() {
        assertFalse(EmailValidator.isValidEmail("name@email"))
    }

    @Test
    fun emailValidator_invalidEmailDoubleDot() {
        assertFalse(EmailValidator.isValidEmail("name@email..com"))
    }

    @Test
    fun emailValidator_invalidEmailNoUsername() {
        assertFalse(EmailValidator.isValidEmail("@email.com"))
    }

    @Test
    fun emailValidator_emptyString() {
        assertFalse(EmailValidator.isValidEmail(""))
    }

    @Test
    fun emailValidator_nullEmail() {
        assertFalse(EmailValidator.isValidEmail(null))
    }
}