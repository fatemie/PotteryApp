package com.example.potteryapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.potteryapp.R
import com.example.potteryapp.databinding.FragmentFormulaDetailBinding

class FormulaDetailFragment : Fragment() {
    lateinit var binding : FragmentFormulaDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormulaDetailBinding.inflate(inflater,container,false)
        return binding.root
    }


}