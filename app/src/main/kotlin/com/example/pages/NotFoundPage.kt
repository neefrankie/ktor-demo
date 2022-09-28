package com.example.pages

import kotlinx.html.HTML
import kotlinx.html.h2

fun HTML.notFoundPage() {
    articleLayout("Not Found") {
        h2 {
            +"The content you are looking for does not exist"
        }
    }
}