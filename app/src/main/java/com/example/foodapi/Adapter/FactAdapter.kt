package com.example.foodapi.Adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapi.R
import com.example.foodapi.databinding.FactItemsBinding


class FactAdapter(private var facts: List<String>) : RecyclerView.Adapter<FactAdapter.FactsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FactItemsBinding.inflate(inflater, parent, false)
        return FactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        holder.bind(facts[position])

    }

    override fun getItemCount(): Int = facts.size



    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newFacts: List<String>) {
        Log.d("FactsAdapter", "Updating adapter with data: $newFacts")
        facts = newFacts
        notifyDataSetChanged()
    }

inner class FactsViewHolder(private val binding: FactItemsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(fact: String) {
        binding.fact.text = fact

    }
}
}
