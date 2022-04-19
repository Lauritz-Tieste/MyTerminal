package de.lauritzdev.myterminal

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView

object MyBluetooth {

    @RequiresApi(Build.VERSION_CODES.M)
    fun initBluetooth(
        context: Context,
        bluetoothManager: BluetoothManager,
        recyclerView: RecyclerView
    ) {
        val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.adapter
        if (bluetoothAdapter == null) {
            Toast.makeText(
                context,
                "Your device doesn't support bluetooth! You can't use this app!",
                Toast.LENGTH_LONG
            ).show()
        }

        if (bluetoothAdapter?.isEnabled == false) {
            Toast.makeText(context, "Please enable bluetooth!", Toast.LENGTH_LONG).show()
        }

        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
        pairedDevices?.forEach { device ->
            val deviceName = device.name
            val deviceHardwareAddress = device.address
            ConnectManager.init(context, recyclerView)
            ConnectManager.addElement(Device(deviceName, deviceHardwareAddress))
        }



        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        bluetoothAdapter?.bondedDevices

    }
}