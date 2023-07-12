package com.example.fitpeoassignment.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpeoassignment.R
import com.example.fitpeoassignment.common.base.Constants
import com.example.fitpeoassignment.common.base.HomeBaseFragment
import com.example.fitpeoassignment.dashboard.album.AlbumListAdapter
import com.example.fitpeoassignment.dashboard.album.AlbumListAdapterVM
import com.example.fitpeoassignment.databinding.FragmentDashboardBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.ref.WeakReference


class DashboardFragment : HomeBaseFragment() {

    lateinit var fragmentDashboardBinding: FragmentDashboardBinding

    enum class Action{
        HIDE_DIALOG, VIEW_ALBUM
    }
    private val dashboardViewModel : DashboardViewModel by viewModels {
        DashboardVMFactory(WeakReference(context))
    }

    val albumListAdapter by lazy {
        AlbumListAdapter(requireContext(), arrayListOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDashboardBinding = FragmentDashboardBinding.inflate(inflater, container, false)
        fragmentDashboardBinding.viewModel = dashboardViewModel

        dashboardViewModel.init(mActivity)

        return fragmentDashboardBinding.root
    }

    override fun onResume() {
        super.onResume()
        loadUI()
    }

    fun loadUI(){
        oberservers()
        setAdapter()
        makeApiCall()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(action: Action) {

        when (action) {

            Action.HIDE_DIALOG -> {
                Log.w("HIDE_DIALOG", "::")
                hideDialog()
            }
            else -> {

            }
        }
    }
    fun makeApiCall(){
        Log.w("makeAPICall", "::" + dashboardViewModel.albumLiveData)
        if (dashboardViewModel.albumLiveData.value == null){
            showDialog()
        }
        lifecycleScope.launch { dashboardViewModel.getAlbumData() }
    }
    fun oberservers(){
        Log.w("oberservers", "::")
        dashboardViewModel.albumListAdapterVM.observe(mActivity, Observer{
            Log.e("into", ":albumListAdapterVM:" )
            albumListAdapter.setData(it)
        })
        dashboardViewModel.albumLiveData.observe(viewLifecycleOwner){
                album ->
            Log.e("into", ":albumLiveData:" )
            dashboardViewModel.albumListAdapterVM.postValue(album)
        }
    }


    fun setAdapter(){
        Log.w("setAdapter", "::")
        fragmentDashboardBinding.rvAlbum.setLayoutManager(
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        )
        fragmentDashboardBinding.rvAlbum.adapter = albumListAdapter
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(albumAction: AlbumListAdapterVM.AlbumAdapterDataModel) {

        when (albumAction.action) {
            Action.VIEW_ALBUM -> {
                Log.w("VIEW_ALBUM", "::" + albumAction)
                val bundle = Bundle()
                bundle.putParcelable(Constants.ALBUM_DATA, albumAction.albumData)
                mActivity.navController.navigate(R.id.album_details_fragment_action, bundle)
            }
            else -> {}
        }
    }
    companion object {

    }
}