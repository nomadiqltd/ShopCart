package com.nomadiq.jamdoughnutshop.domain.model

import com.squareup.moshi.Json

/**
 * @author Michael Akakpo
 *
 * News Article Entity class to represent article details
 *
 */
data class ShopItemDetail(
    @Json(name = "id")
    val id: Int = 1,
    val name: String = "",
    @Json(name = "item_url")
    val imgUrl: String = "",
    @Json(name = "webPublicationDate") // TODO Remove
    val date: String = "", // TODO Remove
    val description: String = "",
    val price: Int = 0,
)
