package com.example.fitpeoassignment.dashboard.album

import android.content.Context
import android.util.Log
import android.view.View
import androidx.annotation.Keep
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitpeoassignment.common.model.dataModel.AlbumData
import com.example.fitpeoassignment.dashboard.DashboardFragment
import org.greenrobot.eventbus.EventBus

class AlbumListAdapterVM(val context: Context, var album: AlbumData): ViewModel() {

    val imageUrl = MutableLiveData<String>()

    fun updateImage(newUrl: String) {
        imageUrl.value = newUrl
    }

    fun getTitle(): String = album.title ?: "--"



    fun viewAlbum (view : View){
        val alData = AlbumAdapterDataModel(album, DashboardFragment.Action.VIEW_ALBUM)
        EventBus.getDefault().post(alData)
    }

    @Keep
    data class AlbumAdapterDataModel(
        var albumData : AlbumData,
        var action: DashboardFragment.Action
        )


}