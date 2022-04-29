package com.example.potteryapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.potteryapp.FormulaAdapter
import com.example.potteryapp.R
import com.example.potteryapp.databinding.FragmentHomeBinding
import com.example.potteryapp.model.Formula
import com.example.potteryapp.viewModels.MainViewModel

class HomeFragment : Fragment() {
    private val vModel: MainViewModel by activityViewModels()
    lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()

    }

    private fun setListener() {
        binding.fab.setOnClickListener{
            goToAddFragment()
        }
    }

    private fun goToAddFragment() {
        findNavController().navigate(R.id.action_homeFragment_to_addFormulaFragment)
    }

    private fun initList() {
        val adapter = FormulaAdapter({ formula -> goToFormulaDetail(formula) })
        binding.formulasRecyclerView.adapter = adapter
        adapter.submitList(vModel.formulas)
    }

    private fun goToFormulaDetail(formula: Formula) {

    }
}