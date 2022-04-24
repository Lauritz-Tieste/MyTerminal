package de.lauritzdev.myterminal

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException

@SuppressLint("StaticFieldLeak")
@RequiresApi(Build.VERSION_CODES.M)
object MyBluetooth {
    private var job: Job? = null
    private lateinit var context: Context

    fun init(c: Context) {
        context = c
        BluetoothConnect(context).connect()

        if (MainActivity.bluetoothSocket != null) {
            // val discoverRequest = OneTimeWorkRequest.Builder(DiscoverWorker::class.java).build()
            // WorkManager.getInstance(context).enqueue(discoverRequest)

            job = GlobalScope.launch { readBtData() }
        } else {
            Msg.e("Bluetooth socket is null")
        }
    }

    private fun readBtData() {
        val buffer = ByteArray(1024)
        val bluetoothSocketInputStream = MainActivity.bluetoothSocket!!.inputStream
        var bytes: Int

        (MainActivity.context as Activity).findViewById<TextView>(R.id.txt_rx)
            .append("---------- STARTED SESSION WITH ${MainActivity.connectedDeviceName} ---------\n\n\n")

        while (MainActivity.isConnected && buffer.isNotEmpty()) {
            bytes = bluetoothSocketInputStream.read(buffer)
            val rxTxt: TextView =
                (MainActivity.context as Activity).findViewById(R.id.txt_rx)

            val oldContent: String = rxTxt.text.toString()
            val readMessage = String(buffer, 0, bytes)

            if (readMessage.length > 39) {
                rxTxt.text = "$oldContent${readMessage.substring(0, 40)}"
            } else {
                rxTxt.text = "$oldContent$readMessage"
            }

            // if(rx_txt.getLineCount() > 5){
            //     val scroll_amount: Int = scroll_amount + rx_txt.getLineHeight();
            // rx_txt.scrollTo(0, scroll_amount);
        }
    }

    fun sendCommand(context: Context, input: String) {
        if (MainActivity.bluetoothSocket != null) {
            try {
                MainActivity.bluetoothSocket!!.outputStream.write(input.toByteArray())
            } catch (e: IOException) {
                Msg.show(context, e.toString())
                e.printStackTrace()
            }
        }
    }

    suspend fun disconnect(context: Context, txtStatus: TextView, statusBar: LinearLayout) {
        if (MainActivity.bluetoothSocket != null) {
            try {
                job!!.cancel()
                delay(100)
                MainActivity.isConnected = false
                delay(100)
                MainActivity.bluetoothSocket!!.close()
                MainActivity.bluetoothSocket = null

                StatusBar.handle(context, txtStatus, statusBar)

            } catch (e: IOException) {
                e.printStackTrace()
                Msg.show(context, e.toString())
            }
        }
    }
}

