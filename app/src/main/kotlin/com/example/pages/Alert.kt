package com.example.pages

import kotlinx.html.FlowContent
import kotlinx.html.div

fun FlowContent.alert(
    msg: String
) {
    div(classes = "alert alert-danger") {
        +msg
    }
}