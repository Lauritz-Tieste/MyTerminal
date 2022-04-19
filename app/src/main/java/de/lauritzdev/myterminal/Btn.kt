package de.lauritzdev.myterminal

import android.content.Context
import android.widget.EditText
import android.widget.Toast

object Btn {

    fun btnClick(context: Context, editText: EditText) {
        Toast.makeText(context, editText.text, Toast.LENGTH_SHORT).show();
    }
}