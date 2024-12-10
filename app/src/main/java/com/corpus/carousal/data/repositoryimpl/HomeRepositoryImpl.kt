package com.corpus.carousal.data.repositoryimpl

import android.content.Context
import com.corpus.carousal.R
import com.corpus.carousal.domain.model.CarousalResponse
import com.corpus.carousal.domain.repository.HomeRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomeRepositoryImpl(private val context: Context) : HomeRepository{

    override suspend fun fetchData(): CarousalResponse {
        val inputStream = context.resources.openRawResource(R.raw.carousal) // "carousal.json" is in the resource/raw folder
        val json = inputStream.bufferedReader().use { it.readText() }
        // Parse the JSON into a list of User objects using Gson
        val listType = object : TypeToken<CarousalResponse>() {}.type
        return Gson().fromJson(json, listType)
    }
}