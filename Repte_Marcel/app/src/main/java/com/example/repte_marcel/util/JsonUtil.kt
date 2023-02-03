package com.example.repte_marcel.util

import android.content.Context
import com.example.repte_marcel.data.TravelPackage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileReader
import java.io.FileWriter

class JsonUtil {

    companion object
    {
        fun getTravelPackages(context: Context): MutableList<TravelPackage> {
            val usersJsonFilePath = context.filesDir.toString() + "/json/package_list.json"
            val usersJsonFile = FileReader(usersJsonFilePath)
            val listUsersType = object: TypeToken<MutableList<TravelPackage>>() {}.type
            val travelPackageList : MutableList<TravelPackage> = Gson().fromJson(usersJsonFile, listUsersType)
            return travelPackageList
        }

        fun saveTravelPackages(context: Context, travelPackageList : MutableList<TravelPackage>){
            val jsonFilePath = context.filesDir.toString() + "/json/package_list.json"
            val jsonFile = FileWriter(jsonFilePath)
            val gson = Gson()
            val jsonElement = gson.toJson(travelPackageList)
            jsonFile.write(jsonElement)
            jsonFile.close()
        }


    }
}