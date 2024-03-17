package com.example.data

import com.example.domain.models.models.IoDispatcher
import com.example.domain.models.repositories.PhotoRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class PhotoRepositoryDefault @Inject constructor(
    private val photoService: PhotoService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : PhotoRepository {
    override fun getPhotos() =
        flow { emit(photoService.getPhotos()) }
            .map { it.mapValue { it.map(PhotoDto::toPhoto) } }
            .flowOn(ioDispatcher)

}
