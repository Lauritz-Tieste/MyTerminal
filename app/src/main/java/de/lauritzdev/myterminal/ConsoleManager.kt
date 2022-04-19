package de.lauritzdev.myterminal

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object ConsoleManager {

    private val itemsList = ArrayList<String>()
    private var customAdapter: ConsoleAdapter = ConsoleAdapter(itemsList)

    fun init(context: Context, recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter
    }

    fun addMessage(text: String) {
        itemsList.add(text)
        customAdapter.notifyDataSetChanged()
    }

    fun addInput(text: String) {
        itemsList.add(text)
        customAdapter.notifyDataSetChanged()
    }

    fun addData(text: String) {
        itemsList.add(text)
        customAdapter.notifyDataSetChanged()
    }
}