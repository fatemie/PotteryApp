package com.example.potteryapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.potteryapp.databinding.FragmentAddFormulaBinding

class AddFormulaFragment : Fragment() {
    lateinit var binding: FragmentAddFormulaBinding
    var llList = listOf<LinearLayout>()
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
            binding.ll1,
            binding.ll2,
            binding.ll3,
            binding.ll4,
            binding.ll5,
            binding.ll6,
            binding.ll7,
            binding.ll8,
            binding.ll9,
            binding.ll10
        )
    }

    private fun setListener() {
        binding.fab.setOnClickListener {
            if(itemCount != 9){
                itemCount++
                llList[itemCount].isVisible = true
            }
        }
        binding.buttonRegister.setOnClickListener {

        }


    }

}