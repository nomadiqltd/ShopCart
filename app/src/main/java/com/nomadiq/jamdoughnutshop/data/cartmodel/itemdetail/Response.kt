package com.nomadiq.jamdoughnutshop.data.cartmodel.itemdetail

data class Response(
    val cashback_percentage: Int = 0,
    val categoryid: Int = 0,
    val description: String = "",
    val id: Int = 0,
    val item_url: String = "",
    val name: String = "",
    val price: Int = 0
)