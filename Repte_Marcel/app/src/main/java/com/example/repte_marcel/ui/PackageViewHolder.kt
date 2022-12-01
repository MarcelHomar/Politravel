package com.example.repte_marcel.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.repte_marcel.data.TravelPackage
import com.example.repte_marcel.databinding.ItemPackageBinding

class PackageViewHolder (
    private val binding : ItemPackageBinding,
    listener: PackageAdapter.PackageAdapterListener
    ): RecyclerView.ViewHolder(binding.root){

    init {
        binding.run {
            this.listener = listener
        }
    }

    fun bind(travelPackage: TravelPackage){
        binding.travelPackage = travelPackage

    }



}