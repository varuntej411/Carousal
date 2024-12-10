package com.corpus.carousal.domain.repository

import com.corpus.carousal.domain.model.CarousalResponse

interface HomeRepository {

    suspend fun fetchData(): CarousalResponse

}