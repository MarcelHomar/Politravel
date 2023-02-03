package com.example.repte_marcel.data

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

class TravelPackage(
    val id : Long,
    var title : String,
    var image : String,
    var aproxTime : String,
    var transport : String,
    var itinerary : List<String>,
    var latitude : String,
    var longitude : String,
    var zoom : String,
    ) : Serializable {
}

object TravelPackageDiffCallback : DiffUtil.ItemCallback<TravelPackage>() {
    override fun areItemsTheSame(oldItem: TravelPackage, newItem: TravelPackage) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: TravelPackage, newItem: TravelPackage) = oldItem == newItem
}