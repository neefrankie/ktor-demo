package com.example.validator

import java.util.regex.Pattern

object Patterns {
    val EMAIL_ADDRESS: Pattern = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
    "\\@" +
    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
    "(" +
    "\\." +
    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
    ")+"
    )
}