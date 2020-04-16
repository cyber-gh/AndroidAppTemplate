package dev.skyit.utilities.others

import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun TextInputEditText.onEdit(callback: (String) -> Unit) {
    this.addTextChangedListener {
        callback(it?.toString() ?: "")
    }
}

val TextInputLayout.text : String
    get() = editText?.text.toString()