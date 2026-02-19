package com.example.davomatdaftarchasi.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.davomatdaftarchasi.R
import com.example.davomatdaftarchasi.Talaba

class DavomatAdapter(
    private val onHolatChange: (talabaId: Int, holat: String) -> Unit
) : RecyclerView.Adapter<DavomatAdapter.DavomatViewHolder>() {

    private var talabalar: List<Talaba> = emptyList()
    private var holatMap: Map<Int, String> = emptyMap()

    fun submitList(list: List<Talaba>, holat: Map<Int, String>) {
        talabalar = list
        holatMap = holat
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DavomatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_davomat, parent, false)
        return DavomatViewHolder(view)
    }

    override fun onBindViewHolder(holder: DavomatViewHolder, position: Int) {
        val talaba = talabalar[position]
        val holat = holatMap[talaba.id] ?: ""
        holder.bind(talaba, holat)
    }

    override fun getItemCount() = talabalar.size

    inner class DavomatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ismText: TextView = itemView.findViewById(R.id.ismText)
        private val holatGroup: RadioGroup = itemView.findViewById(R.id.holatGroup)
        private val rbKeldi: RadioButton = itemView.findViewById(R.id.rbKeldi)
        private val rbKelmagan: RadioButton = itemView.findViewById(R.id.rbKelmagan)
        private val rbSababli: RadioButton = itemView.findViewById(R.id.rbSababli)
        private val rbKechikdi: RadioButton = itemView.findViewById(R.id.rbKechikdi)

        fun bind(talaba: Talaba, holat: String) {
            ismText.text = "${talaba.familiya} ${talaba.ism}"

            if (holat == "kelmagan") {
                ismText.setTextColor(Color.RED)
            } else {
                ismText.setTextColor(Color.BLACK)
            }

            holatGroup.setOnCheckedChangeListener(null)
            holatGroup.clearCheck()

            when (holat) {
                "keldi" -> rbKeldi.isChecked = true
                "kelmagan" -> rbKelmagan.isChecked = true
                "sababli" -> rbSababli.isChecked = true
                "kechikdi" -> rbKechikdi.isChecked = true
            }

            holatGroup.setOnCheckedChangeListener { _, checkedId ->
                val tanlanganHolat = when (checkedId) {
                    R.id.rbKeldi -> "keldi"
                    R.id.rbKelmagan -> "kelmagan"
                    R.id.rbSababli -> "sababli"
                    R.id.rbKechikdi -> "kechikdi"
                    else -> ""
                }
                if (tanlanganHolat.isNotEmpty()) {
                    onHolatChange(talaba.id, tanlanganHolat)
                }
            }
        }
    }
}
