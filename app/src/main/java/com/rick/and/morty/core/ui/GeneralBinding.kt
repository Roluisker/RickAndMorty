package com.rick.and.morty.core.ui

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isLoaderVisible")
fun loader(view: View, isVisible: Boolean) {
    if (isVisible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("isViewVisible")
fun showViews(view: View, isVisible: Boolean) {
    if (isVisible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}