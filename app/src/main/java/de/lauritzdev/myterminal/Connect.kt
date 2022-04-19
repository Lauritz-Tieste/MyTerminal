package de.lauritzdev.myterminal

import android.bluetooth.BluetoothManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_connect.*
import kotlinx.android.synthetic.main.connect_item.*

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