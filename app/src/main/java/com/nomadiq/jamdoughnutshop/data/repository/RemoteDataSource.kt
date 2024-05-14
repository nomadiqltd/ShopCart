package com.nomadiq.jamdoughnutshop.data.repository

import com.nomadiq.jamdoughnutshop.domain.model.CategoryItem
import com.nomadiq.jamdoughnutshop.domain.model.ShopFeedItem
import com.nomadiq.jamdoughnutshop.domain.model.ShopItemDetail
import com.nomadiq.jamdoughnutshop.domain.repository.ShopFeedRepository


/**
 * @author Michael Akakpo
 *
 * This remote data source, fetches data for the [ShopFeedRepository]
 * from a remote source which is the Guardian ceo api.
 *
 */
interface RemoteDataSource {
    suspend fun fetchShopFeedList(): List<ShopFeedItem>
    suspend fun fetchCategoriesFilterList(): List<CategoryItem>
    suspend fun fetchShopItemDetail(productId: Int): ShopItemDetail
}
