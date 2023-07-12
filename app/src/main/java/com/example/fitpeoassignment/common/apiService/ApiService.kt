package com.example.fitpeoassignment.common

import com.example.fitpeoassignment.common.model.dataModel.AlbumData
import com.google.gson.JsonObject
import retrofit2.Call

interface ApiService {

    fun getAlbumData(): Call<List<AlbumData>>?
}