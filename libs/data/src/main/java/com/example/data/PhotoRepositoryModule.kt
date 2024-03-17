package com.example.data

import com.example.domain.models.repositories.PhotoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface PhotoRepositoryModule {
    @Binds
    fun bindPhotoRepository(default: PhotoRepositoryDefault): PhotoRepository
}