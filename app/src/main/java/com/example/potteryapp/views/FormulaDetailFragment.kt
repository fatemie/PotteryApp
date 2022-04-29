package com.example.potteryapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.potteryapp.databinding.FragmentFormulaDetailBinding
import com.example.potteryapp.model.Formula
import com.example.potteryapp.repository.FormulasRepository
import com.example.potteryapp.viewModels.MainViewModel

class FormulaDetailFragment : Fragment() {
    private val vModel: MainViewModel by activityViewModels()
    lateinit var binding: FragmentFormulaDetailBinding
    var formulaId = 0
    lateinit var formula: Formula
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            formulaId = it.getInt("formulaId", 0)
        }
        formula = FormulasRepository.getFormulaWithID(formulaId)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormulaDetailBinding.inflate(inflater, container, false)
        binding.totalAmount = vModel.calculateTotalAmount(formula).toString()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        setListener()
    }

    private fun setListener() {
        binding.button.setOnClickListener {
            val newTotal = binding.editTextTotalAmount.text.toString()
            val totalInt = Integer.parseInt(newTotal)
            formula = vModel.createNewFormula(formula, totalInt)
            initList()
        }
    }

    private fun initList() {
        val adapter = ItemAdapter()
        binding.itemsRecyclerView.adapter = adapter
        adapter.submitList(formula.Compounds)
    }


}