package com.example.potteryapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.potteryapp.R
import com.example.potteryapp.databinding.FragmentAddFormulaBinding
import com.example.potteryapp.model.Formula
import com.example.potteryapp.model.Item
import com.example.potteryapp.repository.FormulasRepository
import com.example.potteryapp.viewModels.MainViewModel

class AddFormulaFragment : Fragment() {
    private val vModel: MainViewModel by activityViewModels()
    lateinit var binding: FragmentAddFormulaBinding
    var llList = listOf<LinearLayout>()
    var nameEditTextLst = listOf<EditText>()
    var amountEditTextLst = listOf<EditText>()
    var descriptionEditTextLst = listOf<EditText>()
    private var itemCount = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddFormulaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setListener()
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

    private fun setListener() {
        binding.fab.setOnClickListener {
            if (itemCount != 9) {
                itemCount++
                llList[itemCount].isVisible = true
            }
        }
        binding.buttonRegister.setOnClickListener {
            var itemLst = arrayListOf<Item>()
            for (i in 0..itemCount) {
                var item = Item(
                    i,
                    nameEditTextLst[i].text.toString(),
                    amountEditTextLst[i].text.toString().toDouble(),
                    descriptionEditTextLst[i].text.toString()
                )
                itemLst.add(item)
            }
            val newFormula = Formula(
                binding.editTextFormulaNumber.text.toString().toInt(),
                "",
                itemCount,
                itemLst
            )
            FormulasRepository.insertFormula(newFormula)
            goBackToHomeFragment()

        }


    }

    private fun goBackToHomeFragment() {
        vModel.updateLst()
        findNavController().navigate(R.id.action_addFormulaFragment_to_homeFragment)
    }

}