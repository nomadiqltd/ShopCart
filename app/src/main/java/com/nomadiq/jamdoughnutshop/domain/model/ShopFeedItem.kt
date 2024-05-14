package com.nomadiq.jamdoughnutshop.domain.model

import com.squareup.moshi.Json


/**
 * @author Michael Akakpo
 *
 * Entity class for [ShopFeedItem], to be used in domain and presentation layers
 *
 */

data class ShopFeedItem(
    @Json(name = "id")
    val id: Int = 1,
    @Json(name = "name")
    val name: String = "",
    val description: String = "",
    @Json(name = "lead_image_urls")
    val imgUrl: String = "",
    @Json(name = "price")
    val price: Int = 0,
    val quantity: Int = 0,
)
