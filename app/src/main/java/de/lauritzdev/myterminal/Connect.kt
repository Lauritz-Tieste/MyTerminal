package de.lauritzdev.myterminal

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_connect.*

@Suppress("DEPRECATION")
@RequiresApi(Build.VERSION_CODES.M)
class Connect : AppCompatActivity() {
    companion object {
        lateinit var context: Context
    }

    private var bluetoothAdapter: BluetoothAdapter? = null
    private lateinit var pairedDevices: Set<BluetoothDevice>
    private val requestEnableBluetooth = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect)
        context = this
        checkPermission()

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter == null) {
            Msg.error(this, context.getString(R.string.bluetooth_doesnt_work), getString(R.string.bt_not_supported))
            return
        }
        if (!bluetoothAdapter!!.isEnabled) {
            val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBluetoothIntent, requestEnableBluetooth)
        }

        pairedDeviceList()

        btn_refresh.setOnClickListener { pairedDeviceList() }
    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
    }


    private fun pairedDeviceList() {
        checkPermission()
        pairedDevices = bluetoothAdapter!!.bondedDevices
        val list: ArrayList<BluetoothDevice> = ArrayList()
        val nameList: ArrayList<String> = ArrayList()

        if (pairedDevices.isNotEmpty()) {
            for (device: BluetoothDevice in pairedDevices) {
                list.add(device)
                nameList.add(device.name)
            }
        } else {
            Msg.error(this, context.getString(R.string.no_devices_found), getString(R.string.bt_no_devices_found))
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nameList)
        device_list.adapter = adapter
        device_list.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val device: BluetoothDevice = list[position]
            val address: String = device.address

            Hawk.init(this).build()
            Hawk.put("address", address)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestEnableBluetooth) {
            if (resultCode == Activity.RESULT_OK) {
                if (bluetoothAdapter!!.isEnabled) {
                    Msg.success(this, context.getString(R.string.bluetooth_activated), getString(R.string.bt_enabled))
                } else {
                    Msg.success(this, context.getString(R.string.bluetooth_deactivated), getString(R.string.bt_disabled))
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Msg.info(
                    this,
                    context.getString(R.string.bluetooth_activation_cancel),
                    getString(R.string.bt_enabling_cancel)
                )
            }
        }
    }
}