package de.lauritzdev.myterminal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

internal class ConnectAdapter(private var itemList: List<Device>) :
    RecyclerView.Adapter<ConnectAdapter.ConnectViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }


    internal inner class ConnectViewHolder(view: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(view) {
        var deviceTextView: TextView = view.findViewById(R.id.device)
        var macTextView: TextView = view.findViewById(R.id.mac)

        init {
            itemView.setOnClickListener { listener.onItemClick(adapterPosition) }
        }
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConnectViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.connect_item, parent, false)
        return ConnectViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ConnectViewHolder, position: Int) {
        val item = itemList[position]
        holder.deviceTextView.text = item.name
        holder.macTextView.text = item.mac
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}