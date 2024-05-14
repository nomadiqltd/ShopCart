package com.nomadiq.jamdoughnutshop.domain.usecase

import com.nomadiq.jamdoughnutshop.domain.model.CategoryItem
import com.nomadiq.jamdoughnutshop.domain.repository.ShopFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * @author Michael Akakpo
 *
 * Use case for loading a list of [CategoryItem] for user to view within a list
 */

class GetShopCategoryFilterUseCase @Inject constructor(
    private val shopFeedRepository: ShopFeedRepository,
) {

    suspend operator fun invoke() : Flow<List<CategoryItem>> {
        val items = shopFeedRepository.fetchCategoryList()
        flow {
            emit(items)
        }
        return flowOf(items)
    }
}

