package com.nomadiq.jamdoughnutshop.domain.usecase

import com.nomadiq.jamdoughnutshop.domain.model.ShopItemDetail
import com.nomadiq.jamdoughnutshop.domain.repository.ShopFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * @author Michael Akakpo
 *
 * Use case for fetching a particular shop item's details [@ShopItemDetailResponse]
 *
 */

class GetShopItemDetailUseCase @Inject constructor(
    private val shopFeedRepository: ShopFeedRepository,
) {

    suspend operator fun invoke(productId: Int): Flow<ShopItemDetail> {
        val item =
            shopFeedRepository.fetchShopItemDetail(productId = productId)
        flow { emit(item) }
        return flowOf(item)
    }
}

