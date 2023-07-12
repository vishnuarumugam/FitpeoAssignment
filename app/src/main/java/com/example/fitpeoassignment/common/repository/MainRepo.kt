package com.example.fitpeoassignment.common


import com.example.fitpeoassignment.common.model.dataModel.AlbumData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class MainRepo(mApiHelper: ApiHelper) {

    private var mApiHelper: ApiHelper = mApiHelper

    suspend fun getAlbumData(): Call<List<AlbumData>>? {
        return withContext(Dispatchers.IO) {mApiHelper.getAlbumData()}
    }
}