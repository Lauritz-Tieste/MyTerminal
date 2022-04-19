package de.lauritzdev.myterminal

import android.bluetooth.BluetoothManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_connect.*

class Connect : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect)

        val bluetoothManager: BluetoothManager =
            getSystemService(BluetoothManager::class.java)

        MyBluetooth.initBluetooth(this, bluetoothManager, connect_recycler_view)
    }
}