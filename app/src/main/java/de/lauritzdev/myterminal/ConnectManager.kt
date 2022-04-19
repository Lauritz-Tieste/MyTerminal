package de.lauritzdev.myterminal

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object ConnectManager {

    private val itemsList = ArrayList<Device>()
    private var connectAdapter: ConnectAdapter = ConnectAdapter(itemsList)

    fun init(context: Context, recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = connectAdapter
        connectAdapter.setOnItemClickListener(object : ConnectAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(
                    context,
                    "connecting to ${itemsList[position].name} ...",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }


    fun addElement(device: Device) {
        if (!itemsList.contains(device)) {
            itemsList.add(device)
            connectAdapter.notifyDataSetChanged()
        }
    }
}