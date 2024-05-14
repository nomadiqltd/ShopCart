package com.nomadiq.jamdoughnutshop.data.cartmodel.itemfeed

data class ShopFeedResponse(
    val categories: List<Category> = listOf(),
    val products: List<Product> = listOf()
)