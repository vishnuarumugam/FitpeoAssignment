package com.example.fitpeoassignment.common

import com.example.fitpeoassignment.common.model.dataModel.AlbumData
import retrofit2.Call


class ApiHelper(mApiService: ApiService) {
    private var mApiService: ApiService = mApiService

    fun getAlbumData(): Call<List<AlbumData>>? {
        return mApiService.getAlbumData()
    }
}