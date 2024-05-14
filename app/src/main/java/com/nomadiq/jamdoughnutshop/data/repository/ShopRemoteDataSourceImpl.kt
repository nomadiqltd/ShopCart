package com.nomadiq.jamdoughnutshop.data.repository

import android.util.Log
import com.nomadiq.jamdoughnutshop.data.cartmodel.itemfeed.ShopFeedResponse
import com.nomadiq.jamdoughnutshop.domain.model.CategoryItem
import com.nomadiq.jamdoughnutshop.domain.model.ShopItemDetail
import com.nomadiq.jamdoughnutshop.domain.model.ShopFeedItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File
import javax.inject.Inject

/**
 * @author Michael Akakpo
 *
 * This data source fetches data remotely,
 * processes the result and allows retrieval and mapping of the API data from the [api]
 *
 */
class ShopRemoteDataSourceImpl @Inject constructor() :
    RemoteDataSource {

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    override suspend fun fetchShopFeedList(): List<ShopFeedItem> = loadShopFeedList()

    override suspend fun fetchCategoriesFilterList(): List<CategoryItem> = loadCategories()

    override suspend fun fetchShopItemDetail(productId: Int): ShopItemDetail =
        loadProductItemDetail(productId)


    private fun loadShopFeedList(): List<ShopFeedItem> {
        // Create a type adapter for the root JSON object
        val filePath = File("api-json/shop_cart_response.json")
        // Read the JSON file
        val json = javaClass.classLoader?.getResourceAsStream(filePath.toString())
            ?.let { input -> input.bufferedReader().use { it.readText() } }

        val parsedString = json?.let {
            val jsonAdapter = moshi.adapter(ShopFeedResponse::class.java)
            jsonAdapter.lenient()
                .fromJson(it)
        }

        val products = parsedString?.products

        val items = products!!.map {
            val product = products.first {
                it.id == it.id
            }
            ShopFeedItem(
                id  = it.id,
                name = it.name,
                description = it.description,
                imgUrl = it.lead_image_urls.first().toString(),
                price = it.price
            )
        }

        return items
    }

    private fun loadProductItemDetail(productId: Int): ShopItemDetail {
        // Create a type adapter for the root JSON object
        val filePath = File("api-json/shop_cart_response.json")
        // Read the JSON file
        val json = javaClass.classLoader?.getResourceAsStream(filePath.toString())
            .let { input -> input!!.bufferedReader().use { it.readText() } }

        val parsedString = json.let {
            val jsonAdapter = moshi.adapter(ShopFeedResponse::class.java)
            jsonAdapter.lenient()
                .fromJson(it)
        }

        val products = parsedString!!.products

        val items = products.map {
            val product = products.first {
                it.id == it.id
            }
            ShopItemDetail(
                id = it.id,
                name = it.name,
                description = it.description,
                imgUrl = it.lead_image_urls.first().toString(),
                price = it.price
            )
        }

        // Search for the desired item
        val searchedItem = items.find { item -> item.id == productId }

        // Return the desired item
        return searchedItem ?: ShopItemDetail(1, "default", "", "")
    }

    private fun loadCategories(): List<CategoryItem> {
        // Create a type adapter for the root JSON object
        val filePath = File("api-json/shop_cart_response.json")
        // Read the JSON file
        val json = javaClass.classLoader?.getResourceAsStream(filePath.toString())
            ?.let { input -> input.bufferedReader().use { it.readText() } }

        val parsedString = json?.let {
            val jsonAdapter = moshi.adapter(ShopFeedResponse::class.java)
            jsonAdapter.lenient()
                .fromJson(it)
        }

        val categories = parsedString?.categories

        val categoryList = categories!!.map {
        Log.d("categories label ==> ", "${it.label}" )
            CategoryItem(it.category_id, it.label)
        }

        Log.d("categories size ==> ", "${categoryList.size}" )

        return categoryList
    }

}
