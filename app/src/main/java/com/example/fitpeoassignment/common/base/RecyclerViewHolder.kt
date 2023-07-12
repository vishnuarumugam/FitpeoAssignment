package com.example.fitpeoassignment.dashboard

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/*
* Base RecyclerView ViewHolder
* */
open class RecyclerViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    var binding: ViewDataBinding? = null

    init {
        bind()
    }

    fun bind() {
        if (binding == null) binding = DataBindingUtil.bind(itemView)
    }

    fun unbind() {
        binding?.unbind()
    }
}