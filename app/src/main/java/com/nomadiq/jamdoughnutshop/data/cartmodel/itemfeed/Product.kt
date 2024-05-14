package com.nomadiq.jamdoughnutshop.data.cartmodel.itemfeed

data class Product(
    val cashback_percentage: Int = 0,
    val category_id: Int = 0,
    val id: Int = 0,
    val lead_image_urls: List<String> = listOf(),
    val name: String = "",
    val description: String = "",
    val price: Int = 0
)