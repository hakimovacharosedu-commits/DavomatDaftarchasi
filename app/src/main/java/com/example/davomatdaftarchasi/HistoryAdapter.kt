package com.example.davomatdaftarchasi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.davomatdaftarchasi.R
import com.example.davomatdaftarchasi.Davomat

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var davomatlar: List<Davomat> = emptyList()

    fun submitList(list: List<Davomat>) {
        davomatlar = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(davomatlar[position])
    }

    override fun getItemCount() = davomatlar.size

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val sanaText: TextView = itemView.findViewById(R.id.sanaText)
        private val holatText: TextView = itemView.findViewById(R.id.holatText)

        fun bind(davomat: Davomat) {
            sanaText.text = davomat.sana

            holatText.text = when (davomat.holat) {
                "keldi" -> "✅ Keldi"
                "kelmagan" -> "❌ Kelmagan"
                "sababli" -> "📋 Sababli"
                "kechikdi" -> "⏰ Kechikdi"
                else -> davomat.holat
            }

            val color = when (davomat.holat) {
                "keldi" -> ContextCompat.getColor(itemView.context, R.color.yashil)
                "kelmagan" -> ContextCompat.getColor(itemView.context, R.color.qizil)
                "sababli" -> ContextCompat.getColor(itemView.context, R.color.sariq)
                "kechikdi" -> ContextCompat.getColor(itemView.context, R.color.toʻqSariq)
                else -> ContextCompat.getColor(itemView.context, R.color.kulrang)
            }
            holatText.setTextColor(color)
        }
    }
}
