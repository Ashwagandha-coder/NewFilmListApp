package com.example.newfilmlistapp.widget

import android.view.View
import com.example.newfilmlistapp.R
import com.google.android.material.snackbar.Snackbar

class ErrorMessage(private val view: View) {

    fun errorMessage() {

        val snackbar = Snackbar.make(view, R.string.error_message, Snackbar.LENGTH_LONG)

        snackbar.setAction("Back") {
            snackbar.dismiss()
        }
        snackbar.show()
    }


}