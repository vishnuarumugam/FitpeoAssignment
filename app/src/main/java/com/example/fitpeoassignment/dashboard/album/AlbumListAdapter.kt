package com.example.fitpeoassignment.dashboard.album

import android.content.Context
import android.util.Log
import com.example.fitpeoassignment.R
import com.example.fitpeoassignment.common.model.dataModel.AlbumData
import com.example.fitpeoassignment.dashboard.RecyclerBaseAdapter
import com.example.fitpeoassignment.dashboard.RecyclerViewHolder
import com.example.fitpeoassignment.databinding.LayAlbumRowBinding
import com.squareup.picasso.Picasso

class AlbumListAdapter(private val context: Context, private var items: ArrayList<AlbumData>) : RecyclerBaseAdapter(){

    private var layAlbumRowBinding: LayAlbumRowBinding? = null
    private var albumListAdapterVM: AlbumListAdapterVM? = null

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.lay_album_row
    }

    override fun getViewModel(holder: RecyclerViewHolder, position: Int): Any? {
        return if (holder.binding is LayAlbumRowBinding) {
            layAlbumRowBinding = holder.binding as LayAlbumRowBinding
            Log.w("getViewModelsetData", "::" + items)
            albumListAdapterVM = AlbumListAdapterVM(context, items[position])
            (layAlbumRowBinding as LayAlbumRowBinding).viewModel = albumListAdapterVM
            albumListAdapterVM
        } else null
    }

    override fun getItemCount() = items.size

    fun setData(_items: List<AlbumData>?) {
        Log.w("setData", "::" + _items)
        items = arrayListOf()
        if (!_items.isNullOrEmpty())
            items.addAll(_items)
        notifyDataSetChanged()
    }

}