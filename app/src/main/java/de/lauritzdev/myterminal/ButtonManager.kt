package de.lauritzdev.myterminal

import android.content.Context
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

object ButtonManager {

    fun onBtnClick(context: Context, editText: EditText, recyclerView: RecyclerView) {
        ConsoleManager.init(context, recyclerView)
        ConsoleManager.addInput(editText.text.toString())
    }
}