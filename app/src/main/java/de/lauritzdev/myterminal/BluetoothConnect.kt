package de.lauritzdev.myterminal

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import java.io.IOException

@Suppress("DEPRECATION")
@RequiresApi(Build.VERSION_CODES.M)
class BluetoothConnect(
    val context: Context,
) {

    fun connect() {
        var connectSuccess = true
        checkPermission()

        try {
            if (MainActivity.bluetoothSocket == null || !MainActivity.isConnected) {
                MainActivity.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
                val device: BluetoothDevice = MainActivity.bluetoothAdapter.getRemoteDevice(
                    MainActivity.deviceAddress
                )
                MainActivity.connectedDevice = device
                MainActivity.connectedDeviceName = device.name
                MainActivity.bluetoothSocket =
                    device.createInsecureRfcommSocketToServiceRecord(MainActivity.myUUID)

                BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
                MainActivity.bluetoothSocket!!.connect()
            }
        } catch (e: IOException) {
            connectSuccess = false
            e.printStackTrace()
        }

        if (connectSuccess) {
            MainActivity.isConnected = true
            Msg.success(
                context,
                context.getString(R.string.connected),
                context.getString(R.string.connect_connected_full)
            )
        }
    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
    }
}