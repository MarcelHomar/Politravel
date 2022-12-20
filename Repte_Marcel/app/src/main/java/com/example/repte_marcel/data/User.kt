package com.example.repte_marcel.data

import java.io.Serializable

class User(
    val id : Long,
    var username : String,
    var profilePicture : String
    ) : Serializable {

}