package com.example.fitpeoassignment.dashboard

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.ref.WeakReference

class DashboardVMFactory(private val weakContext: WeakReference<Context?>) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DashboardViewModel(weakContext) as T
    }
}
