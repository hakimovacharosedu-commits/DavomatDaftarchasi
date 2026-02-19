package com.example.davomatdaftarchasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.davomatdaftarchasi.adapter.HistoryAdapter
import kotlinx.coroutines.launch

class DavomatHistoryFragment : Fragment() {

    companion object {
        private const val ARG_TALABA_ID = "talaba_id"
        private const val ARG_TALABA_NOMI = "talaba_nomi"

        fun newInstance(talabaId: Int, talabaNomi: String): DavomatHistoryFragment {
            val fragment = DavomatHistoryFragment()
            val args = Bundle()
            args.putInt(ARG_TALABA_ID, talabaId)
            args.putString(ARG_TALABA_NOMI, talabaNomi)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var db: AppDatabase
    private lateinit var adapter: HistoryAdapter
    private lateinit var talabaNomi: TextView
    private lateinit var recyclerHistory: RecyclerView
    private lateinit var boshtText: TextView
    private lateinit var umumiyStatText: TextView

    private var talabaId: Int = 0
    private var talabaName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            talabaId = it.getInt(ARG_TALABA_ID)
            talabaName = it.getString(ARG_TALABA_NOMI) ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_davomat_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java,
            "davomat_db"
        ).build()

        initializeViews(view)

        setupRecyclerView()

        historyniYukla()
    }

    private fun initializeViews(view: View) {
        talabaNomi = view.findViewById(R.id.talabaNomi)
        recyclerHistory = view.findViewById(R.id.recyclerHistory)
        boshtText = view.findViewById(R.id.boshtText)
        umumiyStatText = view.findViewById(R.id.umumiyStatText)
    }

    private fun setupRecyclerView() {
        adapter = HistoryAdapter()
        recyclerHistory.layoutManager = LinearLayoutManager(requireContext())
        recyclerHistory.adapter = adapter
    }

    private fun historyniYukla() {
        talabaNomi.text = talabaName

        lifecycleScope.launch {
            val davomatlar = db.davomatDao().talabaningDavomati(talabaId)

            adapter.submitList(davomatlar)

            boshtText.visibility = if (davomatlar.isEmpty()) View.VISIBLE else View.GONE

            val keldi = davomatlar.count { it.holat == "keldi" }
            val kelmagan = davomatlar.count { it.holat == "kelmagan" }
            val sababli = davomatlar.count { it.holat == "sababli" }
            val kechikdi = davomatlar.count { it.holat == "kechikdi" }
            val jami = davomatlar.size

            umumiyStatText.text = "Jami: $jami | ✅ $keldi | ❌ $kelmagan | 📋 $sababli | ⏰ $kechikdi"
        }
    }
}
