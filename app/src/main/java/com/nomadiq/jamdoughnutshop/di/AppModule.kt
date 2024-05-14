package com.nomadiq.jamdoughnutshop.di

import android.content.Context
import com.nomadiq.jamdoughnutshop.di.NetworkModule.provideRemoteDataSource
import com.nomadiq.jamdoughnutshop.di.NetworkModule.provideShopRepository
import com.nomadiq.jamdoughnutshop.domain.usecase.GetShopCategoryFilterUseCase
import com.nomadiq.jamdoughnutshop.domain.usecase.GetShopFeedUseCase
import com.nomadiq.jamdoughnutshop.domain.usecase.GetShopItemDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideGetNewsArticleFeedUseCase(@ApplicationContext context: Context): GetShopFeedUseCase =
        GetShopFeedUseCase(
            shopFeedRepository = provideShopRepository(
                remoteDataSource = provideRemoteDataSource()
            )
        )

    @Provides
    fun provideGetCategoryFilterUseCase(@ApplicationContext context: Context): GetShopCategoryFilterUseCase =
        GetShopCategoryFilterUseCase(
            shopFeedRepository = provideShopRepository(
                remoteDataSource = provideRemoteDataSource()
            )
        )


    @Provides
    fun provideGetNewsArticleItemDetailUseCase(@ApplicationContext context: Context): GetShopItemDetailUseCase =
        GetShopItemDetailUseCase(
            shopFeedRepository = provideShopRepository(
                remoteDataSource = provideRemoteDataSource()
            )
        )
}