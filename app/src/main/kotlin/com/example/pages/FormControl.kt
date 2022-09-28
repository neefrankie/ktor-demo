package com.example.pages

import kotlinx.html.*

fun FORM.textInputControl(
    name: String,
    type: InputType,
    label: String,
    value: String = "",
) {
    div(classes = "mb-2") {
        label(classes = "form-label") {
            this.htmlFor = name
            +label
        }
        input(
            classes = "form-control",
            name = name,
            type = type,
        ) {
            this.id = name
            this.value = value
        }
    }
}

fun FORM.textAreaControl(
    name: String,
    label: String,
    content: String,
) {
    div(classes = "mb-2") {
        label(classes = "form-label") {
            this.htmlFor = name
            +label
        }
        textArea(
            rows = "5",
            classes = "form-control"
        ) {
            this.id = name
            this.name = name
            +content
        }
    }
}

fun FORM.submitButton(
    text: String = "Submit"
) {
    div(classes = "d-grid mt-3") {
        button(
            classes = "btn btn-primary",
            type = ButtonType.submit,
        ) {
            +text
        }
    }
}