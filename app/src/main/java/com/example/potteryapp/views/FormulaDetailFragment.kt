package com.example.potteryapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.potteryapp.R
import com.example.potteryapp.databinding.FragmentFormulaDetailBinding
import com.example.potteryapp.model.Formula
import com.example.potteryapp.model.Item
import com.example.potteryapp.repository.FormulasRepository
import com.example.potteryapp.viewModels.MainViewModel

class FormulaDetailFragment : Fragment() {
    private val vModel: MainViewModel by activityViewModels()
    lateinit var binding: FragmentFormulaDetailBinding
    var llList = listOf<LinearLayout>()
    var nameEditTextLst = listOf<EditText>()
    var amountEditTextLst = listOf<EditText>()
    var descriptionEditTextLst = listOf<EditText>()
    var formulaId = 0
    var flagEdit = false
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
        initViews()
        initList()
        setListener()
    }

    private fun setListener() {
        binding.button.setOnClickListener {
            val newTotal = binding.editTextTotalAmount.text.toString()
            val totalInt = Integer.parseInt(newTotal)
            formula = vModel.createNewFormula(formula, totalInt.toDouble())
            initList()
        }
        binding.buttonDelete.setOnClickListener {
            formula = FormulasRepository.getFormulaWithID(formulaId)!!
            FormulasRepository.deleteFormula(formula)
            goBackToHomeFragment()
        }
        binding.buttonEdit.setOnClickListener {
            showEditViews()
        }
        binding.buttonRegister.setOnClickListener {
            updateFormula()
            binding.llEdit.isVisible = false
            flagEdit = false
        }
    }

    private fun initList() {
        val adapter = ItemAdapter()
        binding.itemsRecyclerView.adapter = adapter
        adapter.submitList(formula.Compounds)
    }

    private fun goBackToHomeFragment() {
        vModel.updateLst()
        findNavController().navigate(R.id.action_formulaDetailFragment_to_homeFragment)
    }

    private fun initViews() {
        llList = listOf(
            binding.ll1, binding.ll2,
            binding.ll3, binding.ll4,
            binding.ll5, binding.ll6,
            binding.ll7, binding.ll8,
            binding.ll9, binding.ll10
        )
        nameEditTextLst = listOf(
            binding.editTextName1, binding.editTextName2,
            binding.editTextName3, binding.editTextName4,
            binding.editTextName5, binding.editTextName6,
            binding.editTextName7, binding.editTextName8,
            binding.editTextName9, binding.editTextName10
        )
        amountEditTextLst = listOf(
            binding.editTextAmount1, binding.editTextAmount2,
            binding.editTextAmount3, binding.editTextAmount4,
            binding.editTextAmount5, binding.editTextAmount6,
            binding.editTextAmount7, binding.editTextAmount8,
            binding.editTextAmount9, binding.editTextAmount10
        )
        descriptionEditTextLst = listOf(
            binding.editTextDescription1, binding.editTextDescription2,
            binding.editTextDescription3, binding.editTextDescription4,
            binding.editTextDescription5, binding.editTextDescription6,
            binding.editTextDescription7, binding.editTextDescription8,
            binding.editTextDescription9, binding.editTextDescription10
        )

    }

    fun showEditViews(){
        binding.llEdit.isVisible = !flagEdit
        flagEdit = !flagEdit
        formula = FormulasRepository.getFormulaWithID(formulaId)!!
        for (i in 0 .. formula.itemCount){
            llList[i].isVisible = true
            nameEditTextLst[i].setText(formula.Compounds[i].name)
            amountEditTextLst[i].setText(formula.Compounds[i].amount.toString())
            descriptionEditTextLst[i].setText(formula.Compounds[i].description)
        }
    }

    fun updateFormula(){
        var itemLst = arrayListOf<Item>()
        for (i in 0..formula.itemCount) {
            var item = Item(
                i,
                nameEditTextLst[i].text.toString(),
                amountEditTextLst[i].text.toString().toDouble(),
                descriptionEditTextLst[i].text.toString()
            )
            itemLst.add(item)
        }
        val updateFormula = Formula(
            formula.id,
            formula.name,
            formula.itemCount,
            itemLst
        )
        FormulasRepository.insertFormula(updateFormula)
        formula = FormulasRepository.getFormulaWithID(formulaId)!!
        initList()
    }


}