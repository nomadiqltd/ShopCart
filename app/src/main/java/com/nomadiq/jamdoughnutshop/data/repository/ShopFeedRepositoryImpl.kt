package com.nomadiq.jamdoughnutshop.data.repository

import android.content.Context
import com.nomadiq.jamdoughnutshop.domain.model.CategoryItem
import com.nomadiq.jamdoughnutshop.domain.model.ShopFeedItem
import com.nomadiq.jamdoughnutshop.domain.model.ShopItemDetail
import com.nomadiq.jamdoughnutshop.domain.repository.ShopFeedRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author Michael Akakpo
 *
 * This [ShopFeedRepository] utilises the remote data source (and potentially others)
 * to aggregate requested data from the [Guardian Api].
 *
 */
class ShopFeedRepositoryImpl @Inject constructor(
    val datasource: RemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher,
) :
    ShopFeedRepository {

    override suspend fun fetchShopFeedList(): List<ShopFeedItem> {
        return withContext(ioDispatcher) { datasource.fetchShopFeedList() }
    }

    override suspend fun fetchCategoryList(): List<CategoryItem> {
        return withContext(ioDispatcher) { datasource.fetchCategoriesFilterList() }
    }

    override suspend fun fetchShopItemDetail(productId: Int): ShopItemDetail {
       return withContext(ioDispatcher) { datasource.fetchShopItemDetail(productId = productId) }
    }
}
