package com.example.davomatdaftarchasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch

class TalabalarFragment : Fragment() {

    private lateinit var db: AppDatabase
    private lateinit var adapter: TalabaAdapter
    private var talabalarList: List<Talaba> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_talabalar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getDatabase(requireContext())

        setupRecyclerView()
        loadTalabalar()

        val addButton: View = view.findViewById(R.id.add)
        addButton.setOnClickListener {
            val fragment = TalabaQoshishFragment.newInstance(null)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.navHostFragment, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setupRecyclerView() {
        adapter = TalabaAdapter(
            onEdit = { talaba ->
                val fragment = TalabaQoshishFragment.newInstance(talaba.id)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.navHostFragment, fragment)
                    .addToBackStack(null)
                    .commit()
            },
            onDelete = { talaba ->
                lifecycleScope.launch {
                    db.talabaDao().ochirish(talaba)
                    loadTalabalar()
                }
            },
            onAttendance = { talaba ->
                val fragment = DavomatHistoryFragment.newInstance(
                    talaba.id, "${talaba.familiya} ${talaba.ism}"
                )
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.navHostFragment, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        )

        val recycler = view?.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerTalabalar)
        recycler?.layoutManager = LinearLayoutManager(requireContext())
        recycler?.adapter = adapter
    }

    private fun loadTalabalar() {
        lifecycleScope.launch {
            talabalarList = db.talabaDao().barchasiniOlish()

            val kelmaganMap = mutableMapOf<Int, Int>()
            for (talaba in talabalarList) {
                kelmaganMap[talaba.id] = db.davomatDao().kelmaganKunlar(talaba.id)
            }

            adapter.submitList(talabalarList, kelmaganMap)
        }
    }
}
