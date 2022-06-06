package com.rick.and.morty.core.ui

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.BaseTransientBottomBar.Duration
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackBar(
    view: View,
    @Duration duration: Int,
    durationInMillis: Int,
    message: String
) {
    var snackBar = Snackbar.make(
        view,
        message,
        duration
    )
    snackBar.duration = durationInMillis
    snackBar.show()
}