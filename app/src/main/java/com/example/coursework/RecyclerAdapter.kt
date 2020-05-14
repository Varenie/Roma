package com.example.coursework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(size: Int): RecyclerView.Adapter<RecyclerAdapter.VHolder>() {
    val size = size
    val priceSingleton = PriceSingleton.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.VHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.recycler_item, parent,false)

        return VHolder(view)
    }

    override fun getItemCount(): Int {
        return size;
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.VHolder, position: Int) {
        holder.bind(position, priceSingleton)
    }

    class VHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val id: TextView = itemView.findViewById(R.id.number)
        val name: TextView = itemView.findViewById(R.id.name_text)
        val description: TextView = itemView.findViewById(R.id.description_text)
        val price: TextView = itemView.findViewById(R.id.price)

        fun bind(position: Int, singleton: PriceSingleton){
            id.text = singleton.id[position].toString()
            name.text = singleton.name[position]
            description.text = singleton.description[position]
            price.text = "Цена: ${singleton.price[position]}р."
        }
    }
}