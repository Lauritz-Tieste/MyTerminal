package de.lauritzdev.myterminal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

internal class ConsoleAdapter(private var itemsList: List<String>) :
    RecyclerView.Adapter<ConsoleAdapter.ConsoleViewHolder>() {

    internal inner class ConsoleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTextView: TextView = view.findViewById(R.id.console_text)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsoleViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.console_item, parent, false)
        return ConsoleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ConsoleViewHolder, position: Int) {
        val item = itemsList[position]
        holder.itemTextView.text = item
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}