package com.nomadiq.jamdoughnutshop.domain.repository

import com.nomadiq.jamdoughnutshop.domain.model.CategoryItem
import com.nomadiq.jamdoughnutshop.domain.model.ShopFeedItem
import com.nomadiq.jamdoughnutshop.domain.model.ShopItemDetail

/**
 * @author Michael Akakpo
 *
 * This interface enables access to and allows CRUD operations on objects being passed through the domain layer (performed by the data layer)
 */
interface ShopFeedRepository {
    suspend fun fetchShopFeedList(): List<ShopFeedItem>
    suspend fun fetchCategoryList(): List<CategoryItem>
    suspend fun fetchShopItemDetail(productId: Int): ShopItemDetail
}
