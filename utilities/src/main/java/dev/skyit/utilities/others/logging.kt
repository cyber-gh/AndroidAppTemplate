package dev.skyit.utilities.others

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment

fun log(message: String) {
    Log.w("qwer", message)
}

fun Context.toastl(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toastl(message: String) {
    context?.toastl(message)
}

fun Context.errAlert(message: String) {
    AlertDialog.Builder(this)
        .setTitle("Error")
        .setMessage(message)
        .show()
}

fun Fragment.errAlert(message: String) {
    context?.errAlert(message)
}