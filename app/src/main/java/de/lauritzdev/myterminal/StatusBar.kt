package de.lauritzdev.myterminal

import android.content.Context
import android.os.Build
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.M)
object StatusBar {
    fun handle(
        context: Context,
        statusBarText: TextView,
        statusBar: LinearLayout,
    ) {
        val statusTXT: String = context.getString(R.string.status)
        if (MainActivity.isConnected) {
            if (MainActivity.connectedDevice != null && MainActivity.connectedDeviceName != null) {
                statusBarText.text =
                    "$statusTXT${context.getString(R.string.status_connected)} - ${MainActivity.connectedDeviceName}"
                statusBar.setBackgroundColor(context.getColor(R.color.status_connected))
            } else {
                statusBarText.text = "$statusTXT${context.getString(R.string.status_connected)}"
                statusBar.setBackgroundColor(context.getColor(R.color.status_connected))
            }
        } else {
            statusBarText.text = "$statusTXT${context.getString(R.string.status_not_connected)}"
            statusBar.setBackgroundColor(context.getColor(R.color.status_disconnected))
        }
    }
}