package com.example.repte_marcel.ui.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repte_marcel.R
import com.example.repte_marcel.data.TravelPackage

class HomeViewModel : ViewModel() {

    private var _travelPackageList = mutableListOf<TravelPackage>()

    var travelPackageListMutable = MutableLiveData(_travelPackageList)


    fun addTravelPackage() {

        _travelPackageList.add(
            TravelPackage(id = 7,
                title = "Angola",
                image = "angola",
                aproxTime = "13 days",
                transport = "Train",
                listOf(
                    "Bali",
                    "Angola",
                    "Budapest"
                ),
                latitude = "",
                longitude = "",
                zoom = "")
        )

        travelPackageListMutable.value = _travelPackageList

    }

    fun sortPackagesByTransport(){
        _travelPackageList.sortBy { item -> item.transport }
        travelPackageListMutable.value = _travelPackageList
    }

    fun initTravelPackages(myList : MutableList<TravelPackage>){

        _travelPackageList = myList
        travelPackageListMutable.value = _travelPackageList

    }


    fun getFromId(id : Long) : TravelPackage? {
        return _travelPackageList.firstOrNull { it.id == id}
    }
}