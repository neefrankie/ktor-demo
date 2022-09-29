package com.example.pages

import kotlinx.html.*

fun FORM.textInputControl(
    name: String,
    type: InputType,
    label: String,
    value: String = "",
    error: String? = null,
    required: Boolean = false,
    desc: String? = null
) {
    div(classes = "mb-2") {
        label(classes = "form-label") {
            this.htmlFor = name
            +label
        }
        input(
            classes = "form-control${if (error.isNullOrBlank()) "" else " is-invalid"}",
            name = name,
            type = type,
        ) {
            this.id = name
            this.value = value
            this.required = required
        }

        if (desc != null) {
            small(classes = "form-text text-muted") {
                +desc
            }
        }

        if (error != null) {
            div(classes = "invalid-feedback") {
                +error
            }
        }
    }
}

fun FORM.textAreaControl(
    name: String,
    label: String,
    content: String,
    required: Boolean = false,
    desc: String? = null,
    error: String? = null,
) {
    div(classes = "mb-2") {
        label(classes = "form-label") {
            this.htmlFor = name
            +label
        }
        textArea(
            rows = "5",
            classes = "form-control${if (error.isNullOrBlank()) "" else " is-invalid"}"
        ) {
            this.id = name
            this.name = name
            this.required = required
            +content
        }

        if (desc != null) {
            small(classes = "form-text text-muted") {
                +desc
            }
        }

        if (error != null) {
            div(classes = "invalid-feedback") {
                +error
            }
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