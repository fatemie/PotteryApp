package com.example.potteryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.potteryapp.model.Formula

typealias FormulaClickHandler = (Formula) -> Unit

class FormulaAdapter(var onFormulasClicked : FormulaClickHandler) :
    ListAdapter<Formula, FormulaAdapter.ViewHolder>(FormulasDiffCallback) {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var btnFormula = view.findViewById<TextView>(R.id.buttonFormula)

        fun bind(formula : Formula, onFormulaClicked: FormulaClickHandler){
            btnFormula.text = formula.id.toString()
            btnFormula.setOnClickListener {
                onFormulaClicked(formula)
            }
        }
    }



    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FormulaAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.formula_item, viewGroup , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FormulaAdapter.ViewHolder, position: Int) {
        val formula = getItem(position)
        holder.bind(formula , onFormulasClicked)
    }

}

object FormulasDiffCallback : DiffUtil.ItemCallback<Formula>() {
    override fun areItemsTheSame(oldItem: Formula, newItem: Formula): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Formula, newItem: Formula): Boolean {
        return oldItem.id == newItem.id
    }
}