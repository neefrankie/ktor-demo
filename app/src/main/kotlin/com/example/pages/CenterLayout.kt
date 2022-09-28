package com.example.pages

import kotlinx.html.DIV
import kotlinx.html.HTML
import kotlinx.html.div

fun HTML.centerLayout(title: String, block: DIV.() -> Unit) {
    baseLayout(title) {
        div(classes = "container") {
            div(classes = "row justify-content-center") {
                div(classes = "col-md-8 col-lg-6", block = block)
            }
        }
    }
}