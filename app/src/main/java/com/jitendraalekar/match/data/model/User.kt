package com.jitendraalekar.match.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val uuid : String,
    val gender : String,
    val title : String,
    val firstName : String,
    val lastName : String,
    val location : String,
    val city : String,
    val state : String,
    val country : String,
    val postCode : Int,
    val latitude : String,
    val longitude : String,
    val timeZoneOffset : String,
    val timeZoneDescription : String,
    val email : String,
    val dob : String,
    val age : Int,
    val nat : String,
    val largePicture: String,
    val mediumPicture: String,
    val thumbnailPicture: String,
    val actionStatus : String? = null

)
