package com.arudo.catatube.utils

import android.content.Context
import android.widget.Toast

fun ToastMessage(context: Context?, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}