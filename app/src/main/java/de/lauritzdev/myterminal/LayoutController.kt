package de.lauritzdev.myterminal

import android.content.Context
import android.widget.Toast

object LayoutController {

    fun init(context: Context) {
        Toast.makeText(context, "App is created", Toast.LENGTH_SHORT).show()
    }
}