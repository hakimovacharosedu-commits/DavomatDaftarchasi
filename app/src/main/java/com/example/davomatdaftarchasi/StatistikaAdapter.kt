package com.example.davomatdaftarchasi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.davomatdaftarchasi.R

class StatistikaAdapter : RecyclerView.Adapter<StatistikaAdapter.StatViewHolder>() {

    data class StatItem(
        val ism: String,
        val sinf: String,
        val keldi: Int,
        val kelmagan: Int,
        val sababli: Int,
        val kechikdi: Int,
        val jami: Int
    )

    private var items: List<StatItem> = emptyList()

    fun submitList(list: List<StatItem>) {
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_statistika, parent, false)
        return StatViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class StatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ismText: TextView = itemView.findViewById(R.id.ismText)
        private val sinfText: TextView = itemView.findViewById(R.id.sinfText)
        private val keldiText: TextView = itemView.findViewById(R.id.keldiText)
        private val kelmaganText: TextView = itemView.findViewById(R.id.kelmaganText)
        private val sababliText: TextView = itemView.findViewById(R.id.sababliText)
        private val kechikdiText: TextView = itemView.findViewById(R.id.kechikdiText)

        fun bind(item: StatItem) {
            ismText.text = item.ism
            sinfText.text = "${item.sinf}-sinf | Jami: ${item.jami} kun"
            keldiText.text = "✅ ${item.keldi}"
            kelmaganText.text = "❌ ${item.kelmagan}"
            sababliText.text = "📋 ${item.sababli}"
            kechikdiText.text = "⏰ ${item.kechikdi}"
        }
    }
}
