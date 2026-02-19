package com.example.davomatdaftarchasi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TalabaAdapter(
    private val onEdit: (Talaba) -> Unit,
    private val onDelete: (Talaba) -> Unit,
    private val onAttendance: (Talaba) -> Unit
) : RecyclerView.Adapter<TalabaAdapter.TalabaViewHolder>() {

    private var talabalar: List<Talaba> = emptyList()
    private var kelmaganlarMap: Map<Int, Int> = emptyMap()

    fun submitList(list: List<Talaba>, kelmaganlar: Map<Int, Int>) {
        talabalar = list
        kelmaganlarMap = kelmaganlar
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TalabaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_talaba, parent, false)
        return TalabaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TalabaViewHolder, position: Int) {
        val talaba = talabalar[position]
        val kelmaganKun = kelmaganlarMap[talaba.id] ?: 0
        holder.bind(talaba, kelmaganKun)
    }

    override fun getItemCount() = talabalar.size

    inner class TalabaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: CardView = itemView.findViewById(R.id.cardTalaba)
        private val ismText: TextView = itemView.findViewById(R.id.ismFamiliyaText)
        private val sinfGuruhText: TextView = itemView.findViewById(R.id.sinfGuruhText)
        private val kelmaganText: TextView = itemView.findViewById(R.id.kelmaganText)
        private val editButton: ImageButton = itemView.findViewById(R.id.editButton)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
        private val historyButton: ImageButton = itemView.findViewById(R.id.historyButton)

        fun bind(talaba: Talaba, kelmaganKun: Int) {
            ismText.text = "${talaba.familiya} ${talaba.ism}"
            sinfGuruhText.text = "${talaba.sinf}-sinf | ${talaba.guruh}-guruh"
            kelmaganText.text = "Kelmagan: $kelmaganKun kun"

            if (kelmaganKun > 5) {
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(itemView.context, R.color.qizilBackground)
                )
                kelmaganText.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.qizil)
                )
            } else {
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(itemView.context, android.R.color.white)
                )
                kelmaganText.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.kulrang)
                )
            }

            editButton.setOnClickListener { onEdit(talaba) }
            deleteButton.setOnClickListener { onDelete(talaba) }
            historyButton.setOnClickListener { onAttendance(talaba) }
        }
    }
}
