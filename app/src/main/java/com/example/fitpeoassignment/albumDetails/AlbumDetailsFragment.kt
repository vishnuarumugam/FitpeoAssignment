package com.example.fitpeoassignment.albumDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.fitpeoassignment.common.base.Constants
import com.example.fitpeoassignment.common.base.HomeBaseFragment
import com.example.fitpeoassignment.databinding.FragmentAlbumDetailsBinding
import com.squareup.picasso.Picasso
import java.lang.ref.WeakReference

class AlbumDetailsFragment : HomeBaseFragment() {


    lateinit var fragmentAlbumDetailsBinding: FragmentAlbumDetailsBinding
    private val albumDetailsViewModel :  AlbumDetailsViewModel by viewModels {
        AlbumDetailsVMFactory(WeakReference(context))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAlbumDetailsBinding = FragmentAlbumDetailsBinding.inflate(inflater, container, false)
        fragmentAlbumDetailsBinding.viewModel = albumDetailsViewModel

        albumDetailsViewModel.init(mActivity)

        val bundle = arguments

        if (bundle != null) {
            if (bundle.containsKey(Constants.ALBUM_DATA)) {
                Log.w("bundle", "::" + bundle.getParcelable(Constants.ALBUM_DATA))
                albumDetailsViewModel.albumData.value = bundle.getParcelable(Constants.ALBUM_DATA)
            }
        }
        observers()

        return fragmentAlbumDetailsBinding.root
    }

    fun observers(){
        Log.w("observers", "::")
        albumDetailsViewModel.albumData.observe(mActivity, Observer{
            albumDetailsViewModel.setAlbumData()
            Picasso.get()
                .load(albumDetailsViewModel.albumData.value?.url)
                .fit()
                .centerCrop()
                .into(fragmentAlbumDetailsBinding.albumImage)
        })
    }

}