package com.test.mobile.di

import com.test.mobile.network.repository.NewsRepository
import com.test.mobile.network.service.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideNewsRepository(
        @NetworkModule.news newsApiService: NewsApiService
    ): NewsRepository {
        return NewsRepository(newsApiService)
    }
}