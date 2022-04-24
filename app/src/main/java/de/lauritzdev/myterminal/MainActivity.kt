package de.lauritzdev.myterminal

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity() {

    companion object {
        var myUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        lateinit var bluetoothAdapter: BluetoothAdapter
        var isConnected: Boolean = false
        lateinit var deviceAddress: String
        var connectedDevice: BluetoothDevice? = null
        var bluetoothSocket: BluetoothSocket? = null
        var connectedDeviceName: String? = null
        lateinit var context: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        if (Hawk.isBuilt()) {
            deviceAddress = Hawk.get("address")
            MyBluetooth.init(this)

            btn_1.setOnClickListener { MyBluetooth.sendCommand(this, et_1.text.toString()) }
            btn_2.setOnClickListener { MyBluetooth.sendCommand(this, et_2.text.toString()) }

            StatusBar.handle(this, txt_status, status_bar)

        } else {
            StatusBar.handle(this, txt_status, status_bar)
        }
    }

    private fun clearConsole() {
        txt_rx.text = ""
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.connect -> {
                startActivity(Intent(this, Connect::class.java))
                true
            }
            R.id.settings -> {
                true
            }
            R.id.disconnect -> {
                GlobalScope.launch { MyBluetooth.disconnect(context, txt_status, status_bar) }
                true
            }
            R.id.save -> {
                true
            }
            R.id.open -> {
                true
            }
            R.id.clear -> {
                clearConsole()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}