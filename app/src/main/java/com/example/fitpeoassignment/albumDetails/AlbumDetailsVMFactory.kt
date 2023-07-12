package com.example.fitpeoassignment.albumDetails

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.ref.WeakReference

class AlbumDetailsVMFactory(private val weakContext: WeakReference<Context?>) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumDetailsViewModel(weakContext) as T
    }
}