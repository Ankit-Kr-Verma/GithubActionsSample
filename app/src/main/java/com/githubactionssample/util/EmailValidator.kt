package com.githubactionssample.util

import android.text.Editable
import android.text.TextWatcher
import java.util.regex.Pattern

object EmailValidator : TextWatcher {

    /**
     * Email validation pattern.
     */
    private val EMAIL_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    private var mIsValid: Boolean = false

    fun isValid(): Boolean {
        return mIsValid
    }

    /**
     * Validates if the given input is a valid email address.
     *
     * @param email        The email to validate.
     * @return {@code true} if the input is a valid email. {@code false} otherwise.
     */
    fun isValidEmail(email: CharSequence?): Boolean {
        return email != null && EMAIL_PATTERN.matcher(email).matches()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(editable: Editable?) {
        mIsValid = isValidEmail(editable)
    }
}