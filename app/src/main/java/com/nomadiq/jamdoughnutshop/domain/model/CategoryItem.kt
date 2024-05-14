package com.nomadiq.jamdoughnutshop.domain.model

import com.squareup.moshi.Json

/**
 * @author Michael Akakpo
 *
 * Entity class for [Category], to be used in domain and presentation layers
 *
 */

data class CategoryItem(
    @Json(name = "category_id")
    val category_id: Int = 1,
    @Json(name = "label")
    val label: String = ""
)