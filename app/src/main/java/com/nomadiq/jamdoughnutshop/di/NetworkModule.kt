package com.nomadiq.jamdoughnutshop.di

import com.nomadiq.jamdoughnutshop.data.repository.ShopRemoteDataSourceImpl
import com.nomadiq.jamdoughnutshop.data.repository.ShopFeedRepositoryImpl
import com.nomadiq.jamdoughnutshop.data.repository.RemoteDataSource
import com.nomadiq.jamdoughnutshop.domain.repository.ShopFeedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideContext(): ApplicationContext = ApplicationContext()

    @Singleton
    @Provides
    fun provideRemoteDataSource(): RemoteDataSource =
        ShopRemoteDataSourceImpl()


    @Singleton
    @Provides
    fun provideShopRepository(remoteDataSource: RemoteDataSource): ShopFeedRepository =
        ShopFeedRepositoryImpl(remoteDataSource, Dispatchers.IO)
}