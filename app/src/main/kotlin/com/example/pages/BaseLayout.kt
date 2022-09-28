package com.example.pages

import kotlinx.html.*

fun HTML.baseLayout(title: String, block: BODY.() -> Unit) {
    head {
        lang = "en"
        meta {
            charset = "UTF-8"
        }
        meta {
            name = "viewport"
            content = "width=device-width, initial-scale=1.0"
        }
        meta {
            httpEquiv = "X-UA-Compatible"
            content = "ie=edge"
        }
        title {
            +title
        }
        link {
            href = "https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css"
            rel = "stylesheet"
        }
    }

    body(block = block)
}