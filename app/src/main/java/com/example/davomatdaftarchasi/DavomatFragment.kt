package com.example.davomatdaftarchasi

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.davomatdaftarchasi.adapter.DavomatAdapter
import kotlinx.coroutines.launch
import java.util.*

class DavomatFragment : Fragment() {

    private var db: AppDatabase? = null
    private var adapter: DavomatAdapter? = null
    private var talabalarList: List<Talaba> = emptyList()
    private var tanlanganSana: String = java.time.LocalDate.now().toString()

    private lateinit var sanaText: TextView
    private lateinit var sanaOzgartirButton: Button
    private lateinit var recyclerDavomat: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_davomat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getDatabase(requireContext())

        sanaText = view.findViewById(R.id.sanaText)
        sanaOzgartirButton = view.findViewById(R.id.sanaOzgartirButton)
        recyclerDavomat = view.findViewById(R.id.recyclerDavomat)

        sanaText.text = "Sana: $tanlanganSana"

        setupRecyclerView()
        setupSanaButton()
        loadTalabalarForDate(tanlanganSana)
    }

    private fun setupRecyclerView() {
        adapter = DavomatAdapter { talabaId, holat ->
            lifecycleScope.launch {
                val davomat = Davomat(
                    talabaId = talabaId,
                    sana = tanlanganSana,
                    holat = holat
                )
                db?.davomatDao()?.saqlash(davomat)
            }
        }

        recyclerDavomat.layoutManager = LinearLayoutManager(requireContext())
        recyclerDavomat.adapter = adapter
    }

    private fun setupSanaButton() {
        sanaOzgartirButton.setOnClickListener {
            val today = Calendar.getInstance()
            val year = today.get(Calendar.YEAR)
            val month = today.get(Calendar.MONTH)
            val day = today.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(
                requireContext(),
                { _, selectedYear, selectedMonth, selectedDay ->
                    tanlanganSana = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
                    sanaText.text = "Sana: $tanlanganSana"

                    loadTalabalarForDate(tanlanganSana)
                },
                year, month, day
            )
            datePicker.show()
        }
    }

    private fun loadTalabalarForDate(sana: String) {
        lifecycleScope.launch {
            try {
                talabalarList = db?.talabaDao()?.barchasiniOlish() ?: emptyList()

                val holatMap = mutableMapOf<Int, String>()
                for (talaba in talabalarList) {
                    val holat = db?.davomatDao()?.holatBySana(talaba.id, sana) ?: ""
                    holatMap[talaba.id] = holat
                }

                adapter?.submitList(talabalarList, holatMap)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
