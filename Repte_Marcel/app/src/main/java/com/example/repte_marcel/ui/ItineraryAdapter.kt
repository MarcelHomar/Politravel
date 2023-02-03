package com.example.repte_marcel.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.repte_marcel.R

class ItineraryAdapter(private val context: Context,
                       private val itinerary: List<String>) :
    RecyclerView.Adapter<ItineraryAdapter.ItineraryViewHolder>()
    {

    private val layout = R.layout.item_itinerary


    class ItineraryViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        val txtItinerary : TextView

        init {

            txtItinerary = view.findViewById(R.id.textItinerary)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItineraryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ItineraryViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItineraryViewHolder, position: Int) {
        val itineraryText = itinerary[position]
        bindItineraryItem(holder, itineraryText)

    }

    fun bindItineraryItem(holder: ItineraryViewHolder, itinerary : String){

        holder.txtItinerary?.text = itinerary
    }

    override fun getItemCount() = itinerary.size


}