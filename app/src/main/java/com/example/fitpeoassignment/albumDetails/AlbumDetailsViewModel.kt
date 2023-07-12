package com.example.fitpeoassignment.albumDetails

import android.content.Context
import android.database.Observable
import android.graphics.drawable.Drawable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitpeoassignment.R
import com.example.fitpeoassignment.common.ApiHelper
import com.example.fitpeoassignment.common.ApiServiceImpl
import com.example.fitpeoassignment.common.MainRepo
import com.example.fitpeoassignment.common.model.dataModel.AlbumData
import java.lang.ref.WeakReference

class AlbumDetailsViewModel(private val weakContext: WeakReference<Context?>) : ViewModel()  {

    private var mActivity: Context? = null
    var albumData = MutableLiveData<AlbumData>()
    var albumTitle = ObservableField("")
    var albumId = ObservableField("")
    var albumImage = MutableLiveData<String>()

    fun init(context: Context) {
        mActivity = context
    }

    fun setAlbumData(){
        albumTitle.set(mActivity?.getString(R.string.title)+" " + albumData.value?.title ?: "--")
        albumId.set(mActivity?.getString(R.string.albumId)+" " + albumData.value?.albumId.toString())
        albumImage.value = albumData.value?.url ?: ""
    }
}