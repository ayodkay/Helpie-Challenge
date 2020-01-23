package com.ayodkay.alpha.helpiechallenge.models

import android.location.Address

data class Users(
    val id:Int,
    val name:String,
    val username:String,
    val address: Address,
    val phone:String,
    val website:String,
    val company: Company

)