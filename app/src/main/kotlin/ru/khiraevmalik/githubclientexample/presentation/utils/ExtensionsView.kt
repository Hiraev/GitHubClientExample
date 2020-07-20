package ru.khiraevmalik.githubclientexample.presentation.utils

import android.view.View

fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.visibleWithCheck(visible: Boolean) {
    if (this.isVisible() != visible) visible(visible)
}

fun View.isVisible() = this.visibility == View.VISIBLE
