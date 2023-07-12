package com.example.fitpeoassignment.common

import com.example.fitpeoassignment.common.model.dataModel.AlbumData
import retrofit2.Call
import retrofit2.http.GET


interface Api {

    @GET("/photos")
    fun getAlbumData(): Call<List<AlbumData>>?
}