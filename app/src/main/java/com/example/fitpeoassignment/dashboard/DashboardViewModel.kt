package com.example.fitpeoassignment.dashboard

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpeoassignment.R
import com.example.fitpeoassignment.common.ApiHelper
import com.example.fitpeoassignment.common.ApiServiceImpl
import com.example.fitpeoassignment.common.MainRepo
import com.example.fitpeoassignment.common.model.dataModel.AlbumData
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.ref.WeakReference

class DashboardViewModel(private val weakContext: WeakReference<Context?>) : ViewModel()  {

    private var mActivity: Context? = null
    var mMainRepo: MainRepo? = null
    var albumListAdapterVM = MutableLiveData<MutableList<AlbumData>?>()
    private val _albumLiveData = MutableLiveData<MutableList<AlbumData>?>()
    val albumLiveData : LiveData<MutableList<AlbumData>?> get()= _albumLiveData
    var dataLoaded = ObservableBoolean(false)
    var hasAlbum = ObservableBoolean(false)

    fun init(context: Context) {
        mActivity = context
        mMainRepo = MainRepo(ApiHelper(ApiServiceImpl()))
    }

    fun getAlbumData(){
        viewModelScope.launch {
            mMainRepo?.getAlbumData()?.enqueue(object :
                Callback<List<AlbumData>> {
                override fun onResponse(call: Call<List<AlbumData>>,
                                        response: Response<List<AlbumData>>
                )
                {
                    Log.w("getAlbumData", ":Response:" + response)
                    if (response.code() == 200){
                        _albumLiveData.postValue(response.body() as MutableList)
                        albumListAdapterVM.postValue(response.body() as MutableList)
                        if ((response.body()?.size ?: 0)>0){
                            dataLoaded.set(true)
                            hasAlbum.set(true)
                        }
                        Log.w("getAlbumData", ":Success:" + response.body())
                    }else{
                        Log.e("getAlbumData", ":FailedOnResponse:" + response)
                        Toast.makeText(mActivity, mActivity?.getString(R.string.api_error), Toast.LENGTH_SHORT).show()
                    }
                    EventBus.getDefault().post(DashboardFragment.Action.HIDE_DIALOG)
                }

                override fun onFailure(call: Call<List<AlbumData>>, t: Throwable) {
                    Log.e("getAlbumData", ":FailedOnResponse:" + t.printStackTrace())
                    EventBus.getDefault().post(DashboardFragment.Action.HIDE_DIALOG)
                    Toast.makeText(mActivity,  mActivity?.getString(R.string.api_error), Toast.LENGTH_SHORT).show()
                }
            })

        }
    }
}