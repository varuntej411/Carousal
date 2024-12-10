package com.corpus.carousal.di

import android.app.Application
import com.corpus.carousal.data.repositoryimpl.HomeRepositoryImpl
import com.corpus.carousal.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHomeRepository(application: Application): HomeRepository {
        return HomeRepositoryImpl(context = application)
    }

}