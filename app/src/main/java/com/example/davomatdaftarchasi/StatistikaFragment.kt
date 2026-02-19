package com.example.davomatdaftarchasi

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.davomatdaftarchasi.adapter.StatistikaAdapter
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class StatistikaFragment : Fragment() {

    private lateinit var db: AppDatabase
    private lateinit var adapter: StatistikaAdapter

    private lateinit var boshlanishSanaText: TextView
    private lateinit var tugashSanaText: TextView
    private lateinit var boshlanishButton: Button
    private lateinit var tugashButton: Button
    private lateinit var filterButton: Button
    private lateinit var sinfSpinner: Spinner
    private lateinit var recyclerStat: RecyclerView
    private lateinit var boshtText: TextView
    private lateinit var umumiyText: TextView

    private var boshlanishSana: String = ""
    private var tugashSana: String = ""
    private val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistika, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getDatabase(requireContext())

        initializeViews(view)

        haftalikSanalarniOlish()

        sinfSpinnerniYukla()

        setupRecyclerView()

        setupClickListeners()

        statistikaniYukla()
    }

    private fun initializeViews(view: View) {
        boshlanishSanaText = view.findViewById(R.id.boshlanishSanaText)
        tugashSanaText = view.findViewById(R.id.tugashSanaText)
        boshlanishButton = view.findViewById(R.id.boshlanishButton)
        tugashButton = view.findViewById(R.id.tugashButton)
        filterButton = view.findViewById(R.id.filterButton)
        sinfSpinner = view.findViewById(R.id.sinfSpinner)
        recyclerStat = view.findViewById(R.id.recyclerStat)
        boshtText = view.findViewById(R.id.boshtText)
        umumiyText = view.findViewById(R.id.umumiyText)
    }

    private fun haftalikSanalarniOlish() {
        val calendar = Calendar.getInstance()
        tugashSana = sdf.format(calendar.time)
        calendar.add(Calendar.DAY_OF_YEAR, -7)
        boshlanishSana = sdf.format(calendar.time)

        boshlanishSanaText.text = "Dan: $boshlanishSana"
        tugashSanaText.text = "Gacha: $tugashSana"
    }

    private fun sinfSpinnerniYukla() {
        lifecycleScope.launch {
            val sinflar = mutableListOf("Barchasi")
            sinflar.addAll(db.talabaDao().barchaSinflar())
            val spinnerAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, sinflar)
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sinfSpinner.adapter = spinnerAdapter
        }
    }

    private fun setupRecyclerView() {
        adapter = StatistikaAdapter()
        recyclerStat.layoutManager = LinearLayoutManager(requireContext())
        recyclerStat.adapter = adapter
    }

    private fun setupClickListeners() {
        boshlanishButton.setOnClickListener {
            sanaTanlash { sana ->
                boshlanishSana = sana
                boshlanishSanaText.text = "Dan: $sana"
            }
        }

        tugashButton.setOnClickListener {
            sanaTanlash { sana ->
                tugashSana = sana
                tugashSanaText.text = "Gacha: $sana"
            }
        }

        filterButton.setOnClickListener {
            statistikaniYukla()
        }

        sinfSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                statistikaniYukla()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun sanaTanlash(callback: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                callback(String.format("%04d-%02d-%02d", year, month + 1, day))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun statistikaniYukla() {
        lifecycleScope.launch {
            val tanlanganSinf = sinfSpinner.selectedItem?.toString() ?: "Barchasi"

            val talabalar = if (tanlanganSinf == "Barchasi") {
                db.talabaDao().barchasiniOlish()
            } else {
                db.talabaDao().sinfBoyichaFilter(tanlanganSinf)
            }

            val davomatlar = db.davomatDao().sanaoraliqDavomat(boshlanishSana, tugashSana)

            data class TalabaStat(
                val ism: String,
                val sinf: String,
                val keldi: Int,
                val kelmagan: Int,
                val sababli: Int,
                val kechikdi: Int,
                val jami: Int
            )

            val statList = mutableListOf<TalabaStat>()
            var umumiyKeldi = 0
            var umumiyKelmagan = 0

            for (talaba in talabalar) {
                val tDavomat = davomatlar.filter { it.talabaId == talaba.id }
                val keldi = tDavomat.count { it.holat == "keldi" }
                val kelmagan = tDavomat.count { it.holat == "kelmagan" }
                val sababli = tDavomat.count { it.holat == "sababli" }
                val kechikdi = tDavomat.count { it.holat == "kechikdi" }

                umumiyKeldi += keldi
                umumiyKelmagan += kelmagan

                statList.add(
                    TalabaStat(
                        ism = "${talaba.familiya} ${talaba.ism}",
                        sinf = talaba.sinf,
                        keldi = keldi,
                        kelmagan = kelmagan,
                        sababli = sababli,
                        kechikdi = kechikdi,
                        jami = tDavomat.size
                    )
                )
            }

            adapter.submitList(statList.map {
                StatistikaAdapter.StatItem(
                    ism = it.ism,
                    sinf = it.sinf,
                    keldi = it.keldi,
                    kelmagan = it.kelmagan,
                    sababli = it.sababli,
                    kechikdi = it.kechikdi,
                    jami = it.jami
                )
            })

            umumiyText.text = "Umumiy: ${talabalar.size} talaba | ✅ $umumiyKeldi | ❌ $umumiyKelmagan"
            boshtText.visibility = if (statList.isEmpty()) View.VISIBLE else View.GONE
        }
    }
}
