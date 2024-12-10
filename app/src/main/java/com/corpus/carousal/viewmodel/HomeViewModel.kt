package com.corpus.carousal.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corpus.carousal.model.CarousalResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStreamReader

class HomeViewModel : ViewModel() {

    private val gson: Gson = Gson()
    // LiveData to hold the list of data
    private val _data = MutableLiveData<List<CarousalResponse>>()
    val data: LiveData<List<CarousalResponse>> get() = _data

    fun loadUsersFromAssets(context: Context) {
        viewModelScope.launch {
            val dataList = loadJsonFromAssets(context)
            _data.value = dataList
        }
    }

    private suspend fun loadJsonFromAssets(context: Context): List<CarousalResponse> {
        return withContext(Dispatchers.IO) {
            val inputStream = context.assets.open("carousal.json")
            val reader = InputStreamReader(inputStream)
            val json = reader.readText()
            reader.close()

            val userListType = object : TypeToken<List<CarousalResponse>>() {}.type
            return@withContext gson.fromJson<List<CarousalResponse>>(json, userListType)
        }
    }
}