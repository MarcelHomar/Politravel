package com.example.repte_marcel.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.repte_marcel.R
import com.example.repte_marcel.data.TravelPackage
import com.example.repte_marcel.data.TravelPackageDiffCallback
import com.example.repte_marcel.databinding.ItemPackageBinding

class PackageAdapter(private val listener : PackageAdapterListener) :
    ListAdapter<TravelPackage, PackageViewHolder>(TravelPackageDiffCallback){


    interface PackageAdapterListener {
        fun onPackageClicked(cardView: View, travelPackage: TravelPackage)
        fun onPackageLongPressed(travelPackage: TravelPackage): Boolean
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageViewHolder {

        return PackageViewHolder(
            ItemPackageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
            false
            ),
            listener
        )

    }

    override fun onBindViewHolder(holder: PackageViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

}
