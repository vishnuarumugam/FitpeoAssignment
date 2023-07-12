package com.example.fitpeoassignment.common

import com.example.fitpeoassignment.common.model.dataModel.AlbumData
import com.google.gson.JsonObject
import retrofit2.Call

class ApiServiceImpl: ApiService {

    override fun getAlbumData(): Call<List<AlbumData>>? {
        return HttpClient().getHttpApi()?.getAlbumData() ?: return null
    }


}