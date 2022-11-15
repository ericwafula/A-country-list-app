package com.welovecrazyquotes.countrylistapp.di

import com.welovecrazyquotes.countrylistapp.data.repository.RepositoryImpl
import com.welovecrazyquotes.countrylistapp.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}