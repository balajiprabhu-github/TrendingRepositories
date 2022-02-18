package com.balajiprabhu.trendingrepositories.view

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.balajiprabhu.trendingrepositories.BR

class CommonViewHolder(private val viewBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewBinding.root) {

    fun <VM> bind(viewModel: VM){
        viewBinding.setVariable(BR.viewModel,viewModel)
        viewBinding.executePendingBindings()
    }

}