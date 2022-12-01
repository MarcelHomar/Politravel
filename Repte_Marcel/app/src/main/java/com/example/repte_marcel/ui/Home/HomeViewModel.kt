package com.example.repte_marcel.ui.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repte_marcel.R
import com.example.repte_marcel.data.TravelPackage

class HomeViewModel : ViewModel() {

    var allTravelPackage = initTravelPackages()

    var TravelPackageListMutable = MutableLiveData(initTravelPackages())


    fun initTravelPackages() : MutableList<TravelPackage>{
        val myList = mutableListOf(
            TravelPackage(id = 1,
                title = "Bali Islands",
                image = "bali",
                aproxTime = "4-5 days",
                transport = "Car"),
            TravelPackage(id = 2,
                title = "Budapest",
                image = "budapest",
                aproxTime = "15 days",
                transport = "Train"),
            TravelPackage(id = 3,
                title = "Bali Islands",
                image = "bali",
                aproxTime = "7 days",
                transport = "Bus"),
            TravelPackage(id = 4,
                title = "Budapest",
                image = "budapest",
                aproxTime = "1 day",
                transport = "Airplane"),
            TravelPackage(id = 5,
                title = "Bali Islands",
                image = "bali",
                aproxTime = "5 days",
                transport = "Boat"),
            TravelPackage(id = 6,
                title = "Budapest",
                image = "budapest",
                aproxTime = "500 days",
                transport = "Airplanes")

        )
        return myList
    }


    fun getFromId(id : Long) : TravelPackage? {
        return allTravelPackage.firstOrNull { it.id == id}
    }
}