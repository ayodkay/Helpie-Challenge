package com.ayodkay.alpha.helpiechallenge.models

data class Address(
    val street:String,
    val suite:String,
    val city:String,
    val zip_code:String,
    val geo:Geo
)