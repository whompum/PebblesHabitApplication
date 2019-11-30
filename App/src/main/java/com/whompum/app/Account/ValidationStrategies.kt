package com.whompum.app.Account

import android.text.TextUtils.isEmpty

object ValidationStrategies {

    /**
     * High-Order Entity responsible for determining if a character is
     * not null or empty.
     */
    val notNullOrEmptyValidation: (CharSequence?) -> Boolean = {
        (it != null && !isEmpty(it))
    }

    /**
     * High-Order Entity responsible for determining if a text's character count is
     * within the maximum allowed character count for a Username
     */
    val usernameSizeConstraintValidation: (CharSequence) -> Boolean = {
        true
    }

    /**
     * High-Order Entity responsible for determining if a text's character count is
     * within the maximum allowed character count for Passwords
     */
    val passwordSizeConstraintValidation: (CharSequence) -> Boolean = {
        true
    }

    /**
     * High-Order Entity responsible for determining if a password entry meets the basic
     * requirements imposed on a password
     */
    val passwordMeetsBasicRequirements: (CharSequence) -> Boolean = {
        true
    }

}