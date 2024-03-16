package com.example.domain.models.usecases

import com.example.domain.models.SubjectInteractor
import com.example.domain.models.models.Photo
import com.example.domain.models.repositories.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPhotosUseCase(private val repository: PhotoRepository) :
    SubjectInteractor<Unit, Result<List<Photo>>>() {

    override fun createObservable(params: Unit): Flow<Result<List<Photo>>> {
        return flow { emit(repository.getPhotos()) }
    }

}