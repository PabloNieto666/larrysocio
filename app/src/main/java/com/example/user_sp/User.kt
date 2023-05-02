package com.example.user_sp

data class User(
    var id: Long,
    var name: String,
    var lastName: String,
    var ulr : String
){
    fun getFullName(): String = "$name $lastName"
}
