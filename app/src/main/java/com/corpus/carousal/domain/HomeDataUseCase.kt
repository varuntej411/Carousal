package com.corpus.carousal.domain

import com.corpus.carousal.domain.model.CarousalResponse
import com.corpus.carousal.domain.repository.HomeRepository
import com.corpus.carousal.utils.APIDataStatus
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetHomeDataUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    operator fun invoke(): Flow<APIDataStatus<CarousalResponse>> = flow {
        try {
            emit(APIDataStatus.LOADING())
            delay(3000L)
            val response = repository.fetchData()
            if (response.responseStatus.statusMessage == "Success") {
                emit(APIDataStatus.SUCCESS(data = response))
            } else {
                emit(APIDataStatus.ERROR(message = response.responseStatus.statusMessage))
            }
        } catch (e: Exception) {
            emit(APIDataStatus.ERROR(message = e.localizedMessage ?: "An Unexpected Error Occurred"))
        }
    }.flowOn(Main)
}