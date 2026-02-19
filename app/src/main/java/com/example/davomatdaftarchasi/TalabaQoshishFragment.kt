package com.example.davomatdaftarchasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class TalabaQoshishFragment : Fragment() {

    companion object {
        private const val ARG_TALABA_ID = "talaba_id"

        fun newInstance(talabaId: Int? = null): TalabaQoshishFragment {
            val fragment = TalabaQoshishFragment()
            val args = Bundle()
            if (talabaId != null) args.putInt(ARG_TALABA_ID, talabaId)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var db: AppDatabase
    private var talabaId: Int? = null
    private var mavjudTalaba: Talaba? = null

    private lateinit var titleText: TextView
    private lateinit var ismEditText: EditText
    private lateinit var familiyaEditText: EditText
    private lateinit var sinfEditText: EditText
    private lateinit var guruhEditText: EditText
    private lateinit var saqlashButton: Button
    private lateinit var bekorButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        talabaId = arguments?.getInt(ARG_TALABA_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_talaba_qoshish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getDatabase(requireContext())

        initializeViews(view)
        mavjudTalabaniYukla()
        setupClickListeners()
    }

    private fun initializeViews(view: View) {
        titleText = view.findViewById(R.id.titleText)
        ismEditText = view.findViewById(R.id.ismEditText)
        familiyaEditText = view.findViewById(R.id.familiyaEditText)
        sinfEditText = view.findViewById(R.id.sinfEditText)
        guruhEditText = view.findViewById(R.id.guruhEditText)
        saqlashButton = view.findViewById(R.id.saqlashButton)
        bekorButton = view.findViewById(R.id.bekorButton)
    }

    private fun mavjudTalabaniYukla() {
        if (talabaId == null) {
            titleText.text = "Yangi Talaba"
            return
        }

        lifecycleScope.launch {
            val talaba = db.talabaDao().idBoyichaOlish(talabaId!!)
            mavjudTalaba = talaba
            talaba?.let {
                titleText.text = "Talabani Tahrirlash"
                ismEditText.setText(it.ism)
                familiyaEditText.setText(it.familiya)
                sinfEditText.setText(it.sinf)
                guruhEditText.setText(it.guruh)
            }
        }
    }

    private fun setupClickListeners() {
        saqlashButton.setOnClickListener { saqlash() }
        bekorButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun saqlash() {
        val ism = ismEditText.text.toString().trim()
        val familiya = familiyaEditText.text.toString().trim()
        val sinf = sinfEditText.text.toString().trim()
        val guruh = guruhEditText.text.toString().trim()

        if (ism.isEmpty() || familiya.isEmpty() || sinf.isEmpty() || guruh.isEmpty()) {
            Toast.makeText(requireContext(), "Barcha maydonlarni to'ldiring!", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            if (mavjudTalaba != null) {
                val updated = mavjudTalaba!!.copy(
                    ism = ism,
                    familiya = familiya,
                    sinf = sinf,
                    guruh = guruh
                )
                db.talabaDao().tahrirlash(updated)
                Toast.makeText(requireContext(), "Talaba yangilandi", Toast.LENGTH_SHORT).show()
            } else {
                val yangiTalaba = Talaba(
                    ism = ism,
                    familiya = familiya,
                    sinf = sinf,
                    guruh = guruh
                )
                db.talabaDao().qoshish(yangiTalaba)
                Toast.makeText(requireContext(), "Talaba qo'shildi", Toast.LENGTH_SHORT).show()
            }
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}
