package dev.skyit.utilities.validation

import android.content.Context
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import dev.skyit.utilities.R
import java.util.regex.Pattern


typealias Validator = (String) -> Boolean

class InputValidator(
    val editText: EditText?,
    val errorMsg: String,
    val checker: (String) -> Boolean
) {


    companion object {
        fun nonEmptyValidator(editText: EditText, context: Context): InputValidator {
            return InputValidator(editText, context.getString(R.string.must_not_be_empty)) {
                it.isNotBlank()
            }
        }

        fun atLeastNCharacters(editText: EditText, n: Int, context: Context): InputValidator {
            return InputValidator(
                editText,
                context.getString(R.string.minimum_length) + n.toString()
            ) {
                it.length >= n
            }
        }

        fun validEmail(editText: EditText, context: Context): InputValidator {
            return InputValidator(editText, context.getString(R.string.not_valid_email)) {
                isEmailValid(it)
            }
        }

        private fun isEmailValid(email: String): Boolean {
            return Pattern.compile(
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
            ).matcher(email).matches()
        }
    }
}

class InputValidatorManager {

    private val validators = mutableListOf<InputValidator>()

    private var wasTriggered = false

    fun addInputValidator(inValid: InputValidator) {
        validators.add(inValid)
        inValid.editText?.addTextChangedListener {
            if (!wasTriggered || it == null) return@addTextChangedListener
            val str = it.toString().trim()
            val isGood = inValid.checker(str)
            if (!isGood) {
                inValid.editText.error = inValid.errorMsg
            } else {
                inValid.editText.error = null
            }
        }
    }

    fun runValidations(shouldAcitvate: Boolean = true): Boolean {
        var isGood = true
        wasTriggered = shouldAcitvate
        validators.forEach {
            if (it.editText != null) {
                val str = it.editText.text.toString().trim()
                val res = it.checker(str)
                if (!res) {
                    isGood = false
                    it.editText.error = it.errorMsg
                } else {
                    it.editText.error = null
                }
            }
        }
        return isGood
    }

    fun reset() {
        wasTriggered = false
        validators.forEach {
            it.editText?.requestFocus()
            it.editText?.error = null
            it.editText?.clearFocus()
        }

        //runValidations(false)
    }
}